@echo off
set datetime=%DATE:/=-%-%time::=-%
set name=%datetime: =-%


if not exist "logs" mkdir logs
cd logs

mkdir %name:.=-%
cd %name:.=-%

scp -r admin@roboRIO-6036-FRC.local:/home/admin/logs/ .
