#/bin/sh

zip -r javatpl22_`date "+%y%m%d%H%M%S"`.zip javatpl22/env -x "*.class" -x "*.exe"