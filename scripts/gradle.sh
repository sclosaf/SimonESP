#!/bin/bash

PROJECT_ROOT=$(dirname "$(dirname "$0")")
APP_APK_DEBUG="$PROJECT_ROOT/app/build/outputs/apk/debug/app-debug.apk"
APP_APK_RELEASE="$PROJECT_ROOT/app/build/outputs/apk/release/app-release.apk"
PACKAGE_NAME="unipd.esp2526.Simon"
MAIN_ACTIVITY=".MainActivity"

usage()
{
    echo "Usage: $0 [build|install|deploy|run|log|clean] [verbose]"
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
else
    GRADLE_FLAGS="--warning-mode none"
fi

cd "$PROJECT_ROOT/.." || { echo "Failed to enter project root"; exit 1; }

case $COMMAND in

    build)
        echo "Running full build and unit tests..."
        gradle8 build $GRADLE_FLAGS
        ;;

    install)
        echo "Building and installing release APK..."
        gradle8 assembleRelease $GRADLE_FLAGS && adb install -r "$APP_APK_RELEASE"
        ;;

    deploy)
        echo "Full Deploy: Building, Installing and Running debug APK..."
        gradle8 assembleDebug $GRADLE_FLAGS && \
        adb install -r "$APP_APK_DEBUG" && \
        echo "Starting $PACKAGE_NAME..." && \
        adb shell am start -n "$PACKAGE_NAME/$MAIN_ACTIVITY"
        ;;

    run)
        echo "Starting app on device..."
        adb shell am start -n "$PACKAGE_NAME/$MAIN_ACTIVITY"
        ;;

    clean)
        echo "Cleaning project build directories..."
        gradle8 clean $GRADLE_FLAGS
        rm -rf "$PROJECT_ROOT/build/" "$PROJECT_ROOT/app/build/"
        echo "Local build directories removed."
        ;;

    log)
        echo "Showing logs (Ctrl+C to stop)..."
        adb logcat -c
        PID=$(adb shell pidof -s $PACKAGE_NAME)
        if [ -n "$PID" ]; then
            adb logcat -v brief --pid=$PID
        fi
        ;;

    *)
        usage
        ;;
esac
