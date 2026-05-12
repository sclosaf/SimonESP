# Simon ESP
This is the implementation of the final assignment for the course "Embedded Systems Programming", 2025 - 2026.
The project has been implemented by **Sclosa Fabrizio, 2101826**.

## Development Environment
The following development environment has been used:
- **Operating System**: EndeavourOS - Linux
- **Tested Devices**:
  - *Physical*: Samsung Galaxy A34 5G (Model: SM-A346B), running Android 16
  - *Physical*: Samsung Galaxy S21 Ultra 5G (Model: SM-G998B), running Android 15
  - *Emulator*: Unused;
- **Android Studio**: Unused
- **Gradle version**: 8
- **Min SDK**: API 33 – Android 13
- **Target SDK**: API 35 – Android 15
- **Supported languages**: English, Italian

## Repository Structure
The root directory contains:
- The `app/` folder, which is divided into:
  - The `src/main/kotlin` directory, which contains the Kotlin source code
  - The `src/main/res` directory, which contains the resources such as strings and images
  - The `src/main/AndroidManifest.xml` defines the application configuration
- The `workspace/` folder which contains the assignments `ProjectESP2526v1.pdf` and `ProjectESP2526v2.pdf`, containing the guidelines for the current and previous assignment
- The `scripts/` folder with the utility scripts used to build and install the app on the devices
- The build generated files (`build/`, `app/build/`, `local.properties`, etc.) are not included in the repository, as defined by the `.gitignore`
- The remaining files define the gradle dependencies and the build settings
