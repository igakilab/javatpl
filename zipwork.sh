#/bin/sh

zip -r javatpl21_`date "+%y%m%d%H%M%S"`.zip javatpl21/env -x "*.class" -x "*.exe"