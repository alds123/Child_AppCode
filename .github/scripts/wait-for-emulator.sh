#!/bin/bash
set -e

boot_completed=""

echo "⏳ Waiting for Android emulator to finish booting..."

# Wait up to 5 minutes for emulator to boot
for i in {1..60}; do
  boot_completed=$(adb shell getprop sys.boot_completed 2>/dev/null | tr -d '\r')
  if [[ "$boot_completed" == "1" ]]; then
    echo "✅ Emulator booted!"
    exit 0
  fi
  echo "⏱️ Still waiting... (${i}s)"
  sleep 10
done

echo "❌ Emulator boot timed out!"
exit 1
