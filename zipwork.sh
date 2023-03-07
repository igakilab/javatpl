#/bin/sh

zip -r javatpl23_`date "+%y%m%d%H%M%S"`.zip javatpl23/env -x "*.class" -x "*.exe"