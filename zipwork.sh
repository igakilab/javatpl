#/bin/sh

zip -r javatpl20_`date "+%y%m%d%H%M%S"`.zip javatpl20/env -x "*.class" -x "*.exe"