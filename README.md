# **Cookie Analyzer**

## **Overview**
Cookie Analyzer is a command-line tool that finds the most active cookies from a log file for a given date.  

## **Prerequisites**
- Kotlin 2.1.10
- JDK 21
- Gradle

## **Installation**
Clone the repository and navigate to the project folder:
```sh
git clone <repo-url>
cd cookie-analyzer
```
Build the project using Gradle:
```sh
./gradlew shadowJar  # Linux/macOS  
gradlew.bat shadowJar  # Windows  
```
## **Running Tests**
To run unit tests:
```sh
./gradlew test
```

## **Usage**

### **Linux/macOS**
```sh
chmod +x cookie-analyzer  
./cookie-analyzer -f cookie_log.csv -d 2018-12-09  
```

### **Windows**
```sh
.\cookie-analyzer.cmd -f cookie_log.csv -d 2018-12-09  
```

## **Example Input File (`cookie_log.csv`)**
```
cookie,timestamp  
AtY0laUfhglK3lC7,2018-12-09T14:19:00+00:00  
SAZuXPGUrfbcn5UA,2018-12-09T10:13:00+00:00  
5UAVanZf6UtGyKVS,2018-12-09T07:25:00+00:00  
AtY0laUfhglK3lC7,2018-12-09T06:19:00+00:00  
SAZuXPGUrfbcn5UA,2018-12-08T22:03:00+00:00  
```

### **Expected Output for `2018-12-09`**
```
AtY0laUfhglK3lC7
```

