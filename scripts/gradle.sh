#!/bin/bash

PROJECT_ROOT=$(dirname "$(dirname "$0")")
APP_APK_DEBUG="$PROJECT_ROOT/app/build/outputs/apk/debug/app-debug.apk"
APP_APK_RELEASE="$PROJECT_ROOT/app/build/outputs/apk/release/app-release.apk"
PACKAGE_NAME="unipd.esp2526.Simon"
MAIN_ACTIVITY=".MainActivity"

usage()
{
    echo "Usage: $0 [debug|release|install-debug|install-release|run] [verbose]"
    exit 1
}

if [ $# -lt 1 ]; then
    usage
fi

COMMAND=$1
VERBOSE=$2

GRADLE_FLAGS=""
if [ "$VERBOSE" == "verbose" ]; then
    GRADLE_FLAGS="--stacktrace --info --debug --warning-mode all"
fi

cd "$PROJECT_ROOT/.." || { echo "Failed to enter project root"; exit 1; }

case $COMMAND in
    debug)
        echo "Building debug APK..."
        gradle8 assembleDebug $GRADLE_FLAGS
        ;;

    release)
        echo "Building release APK..."
        gradle8 assembleRelease $GRADLE_FLAGS
        ;;

    install-debug)
        echo "Building and installing debug APK..."
        gradle8 assembleDebug $GRADLE_FLAGS
        adb install -r "$APP_APK_DEBUG"
        ;;

    install-release)
        echo "Building and installing release APK..."
        gradle8 assembleRelease $GRADLE_FLAGS
        adb install -r "$APP_APK_RELEASE"
        ;;

    run)
        echo "Starting app on device..."
        adb shell am start -n "$PACKAGE_NAME/$MAIN_ACTIVITY"
        ;;

    *)
        usage
        ;;
esac
