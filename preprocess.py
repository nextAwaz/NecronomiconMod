#!/usr/bin/env python3
"""
Preprocessor that handles //#if / //#else / //#endif / //$$ directives.
Replaces the functionality of DeftuGradle's multiversion preprocessor.

Usage:
  python3 preprocess.py <target> <src_dir> <dst_dir>

Targets:
  fabric  - FABRIC=1, FORGE=0
  forge   - FORGE=1, FABRIC=0

Directives:
  //#if FABRIC==1     - Include block only for fabric
  //#if FORGE==1      - Include block only for forge
  //#if FABRIC==0     - Include block only for non-fabric (i.e. forge)
  //#else             - Alternate block
  //#endif            - End of conditional block
  //$$ <code>         - Forge-only line (uncomment on forge, strip on fabric)
"""

import os
import sys
import shutil
import re

def preprocess_file(src_path, dst_path, is_fabric):
    """Preprocess a single file, writing to dst_path."""
    with open(src_path, 'r', encoding='utf-8') as f:
        lines = f.readlines()

    result = []
    stack = []  # Stack of (in_else, should_include_original)

    for line in lines:
        stripped = line.lstrip()

        # Check for preprocessor directives
        if stripped.startswith('//#if '):
            condition = stripped[len('//#if '):].strip()
            # Evaluate condition
            if condition == 'FABRIC==1':
                cond_true = is_fabric
            elif condition == 'FORGE==1':
                cond_true = not is_fabric
            elif condition == 'FABRIC==0':
                cond_true = not is_fabric
            elif condition == 'FORGE==0':
                cond_true = is_fabric
            else:
                print(f"  Warning: Unknown condition '{condition}' in {src_path}")
                cond_true = True

            stack.append({
                'in_else': False,
                'should_include': cond_true,
                'had_active_block': cond_true
            })
            continue

        elif stripped.startswith('//#else'):
            if not stack:
                print(f"  Error: #else without #if in {src_path}")
                continue
            stack[-1]['in_else'] = True
            continue

        elif stripped.startswith('//#endif'):
            if not stack:
                print(f"  Error: #endif without #if in {src_path}")
                continue
            stack.pop()
            continue

        # Determine if this line should be included
        include = True
        for frame in stack:
            in_else = frame['in_else']
            should_include = frame['should_include']
            # If block should be included:
            #   - before else: include if should_include
            #   - after else: include if not should_include
            if in_else:
                if should_include:
                    include = False
                    break
            else:
                if not should_include:
                    include = False
                    break

        if include:
            # Handle //$$ lines (forge-only)
            if stripped.startswith('//$$ '):
                if is_fabric:
                    # Strip the //$$ prefix on fabric
                    line = line.replace('//$$ ', '', 1)
                else:
                    # Strip the //$$ prefix on forge too (uncomment it)
                    line = line.replace('//$$ ', '', 1)
            elif stripped.lstrip().startswith('//$$'):
                # Handle edge case: "//$$" without trailing space
                prefix = line[:len(line) - len(stripped)]
                if is_fabric:
                    continue  # Skip on fabric
                else:
                    # Uncomment on forge
                    line = line.replace('//$$', '', 1)

            # Remove empty //#if guards that became empty due to other processing
            result.append(line)

    # Write preprocessed file
    os.makedirs(os.path.dirname(dst_path), exist_ok=True)
    with open(dst_path, 'w', encoding='utf-8') as f:
        f.writelines(result)


def main():
    if len(sys.argv) < 4:
        print("Usage: preprocess.py <target> <src_dir> <dst_dir>")
        print("  target: fabric | forge")
        sys.exit(1)

    target = sys.argv[1].lower()
    src_dir = sys.argv[2]
    dst_dir = sys.argv[3]

    if target == 'fabric':
        is_fabric = True
    elif target == 'forge':
        is_fabric = False
    else:
        print(f"Unknown target: {target}")
        sys.exit(1)

    print(f"Preprocessing for {'fabric' if is_fabric else 'forge'}...")
    print(f"  Source: {src_dir}")
    print(f"  Dest:   {dst_dir}")

    # Clean destination
    if os.path.exists(dst_dir):
        shutil.rmtree(dst_dir)

    # Walk source directory
    file_count = 0
    for root, dirs, files in os.walk(src_dir):
        for file in files:
            src_path = os.path.join(root, file)
            rel_path = os.path.relpath(src_path, src_dir)
            dst_path = os.path.join(dst_dir, rel_path)

            ext = os.path.splitext(file)[1].lower()
            if ext in ('.java', '.json', '.toml', '.mcmeta', '.properties', '.kts', '.yml'):
                preprocess_file(src_path, dst_path, is_fabric)
                file_count += 1
            else:
                # Copy binary or non-text files as-is
                os.makedirs(os.path.dirname(dst_path), exist_ok=True)
                shutil.copy2(src_path, dst_path)
                file_count += 1

    print(f"Processed {file_count} files.")


if __name__ == '__main__':
    main()
