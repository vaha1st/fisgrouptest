#!/bin/bash

# Создание псевдонима для команды mysql
alias mysql=/usr/local/mysql/bin/mysql

# Выбор пользователя и запуск скриптов
echo "Введите пароль от mysql root пользователя"
mysql --user=root -p <<EOF
source sql/create_db.sql
source sql/fillup_db.sql
EOF