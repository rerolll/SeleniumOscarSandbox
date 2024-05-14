# SeleniumOscarSandbox
Доброго времени суток, рад видеть вас в моем проекте CV!
Этот проект покажет вам мои знания в работе с Java, Selenium WebDriver, Selenium Grid, Jenkins, JIRA, Docker, IntelliJ IDEA, Page Object, Ubuntu и, конечно же, GitHub!

# Как запустить проект на компьютере:
- Клонировать репозиторий:
```
https://github.com/rerolll/SeleniumOscarSandbox.git
```
- В корне проекта создать файл config.json
```
  Пример для секретного файла
{
  "valid_email": "213123212@mail.ru",
  "valid_password": "Wsad1as2dsaS",
  "testRail_email": "1@gmail.com",
  "testRail_password": "Password!",
  "testRail_baseUrl": "https://rerolll.testrail.io/",
}
```
- Запустить тесты командой:
```
mvn clean test
```

# Проект так же можно реализовать на сервере 
Как это сдлеать на примере Ubuntu 20.04 LTS aarch64_pi4:
Fork'нуть репозиторий 
Последовательность команд на сервере:
```
sudo apt update //Обновляет список пакетов

sudo apt upgrade //Обновляет установленные пакеты

sudo apt install openjdk-17-jdk //Устанавливает JDK версии 17

sudo apt install maven //Устанавливает сборщик проектов Maven

sudo apt install git //Устанавливает систему контроля версий Git

wget -q -O - https://pkg.jenkins.io/debian-stable/jenkins.io.key | sudo apt-key add - //Скачивает публичный ключ Jenkins и добавляет его в список ключей APT

sudo sh -c 'echo deb https://pkg.jenkins.io/debian-stable binary/ > /etc/apt/sources.list.d/jenkins.list' //Добавляет репозиторий Jenkins в список репозиториев APT

sudo apt update //Обновляет список пакетов после добавления нового репозитория Jenkins

sudo apt install jenkins //Устанавливает Jenkins CI/CD систему

sudo systemctl start jenkins //Запускает службу Jenkins

sudo systemctl enable jenkins //Включает автозапуск Jenkins при загрузке системы

sudo apt install apt-transport-https ca-certificates curl software-properties-common //Устанавливает необходимые пакеты для работы с HTTPS и репозиториями

curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add - //Скачивает и добавляет GPG ключ репозитория Docker

sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" //Добавляет репозиторий Docker в список репозиториев APT

sudo apt update //Обновляет список пакетов после добавления нового репозитория Docker

sudo apt install docker-ce //Устанавливает Docker Community Edition

docker pull seleniarm/standalone-chromium //Скачивает образ Selenium Standalone Server с ChromeDriver на архитектуру ARM

docker run -d -p 4444:4444 -p 7900:7900 --shm-size="2g" seleniarm/standalone-chromium //Запускает контейнер Docker собразом Selenium Standalone Server с ChromeDriver на портах 4444 и 7900, с размером виртуальной памяти 2гб.
```
Теперь нам должен быть доступен:
- Jenkins по адресу <ip вашего сервера>:8080
- Selenium Grid по адресу <ip вашего сервера>:4444

Нам нужно настроить jenkins
```
/var/lib/jenkins/secrets/initialAdminPassword
```
Этот файл содержит ключ, который необходимо скопировать и вставить в окно регистрации Jenkins при первом запуске.
Далее нам надо:
- Создать пользователя
- Установить рекомендуемые плагины
- Создать item со свободной конфигурацией
- Указать Repository URL
- Добавить Credentials для доступа jenkins к git
// если не работает ssh: Настройки Jenkins > Security > Git Host Key Verification Configuration > Host Key Verification Strategy > No verification
- Указать */remote в Branches to build
- В среде сборки создать секретный файл с Variable = CONFIG_PATH
  ```
  Пример для секретного файла
  {
  "valid_email": "213123212@mail.ru",
  "valid_password": "Wsad1as2dsaS",
  "testRail_email": "1@gmail.com",
  "testRail_password": "Password!",
  "testRail_baseUrl": "https://rerolll.testrail.io/",
  }
  ```
- Добавить шаг сборки Выполнить команду shell
  ```
  mvn clean test -DUrlChrome=http://<ip вашего сервера>:4444/wd/hub
  ```
  
