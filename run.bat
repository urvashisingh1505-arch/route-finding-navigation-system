@echo off
echo Compiling...
javac -d out src\*.java
if %errorlevel% neq 0 (
  echo Compilation failed. Check Java is installed.
  pause
  exit /b
)
echo Running Route Finding Navigation System...
java -cp out RouteFinderApp
pause
