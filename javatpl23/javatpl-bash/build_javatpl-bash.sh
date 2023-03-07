go mod init javatpl-bash
./rsrc.exe -ico javatpl-bash.ico -o javatpl-bash.syso
GOOS=windows GOARCH=amd64 go build -ldflags -H=windowsgui