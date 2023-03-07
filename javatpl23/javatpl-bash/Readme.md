# bash.exeを呼び出すgoプログラム
## ビルド環境設定
- msys2 portableをダウンロードして展開
  - https://sourceforge.net/projects/mwayne/files/MSYS2Portable/
- golang環境を導入
  - 参考：http://takaya030.hatenablog.com/entry/2018/01/18/230105
```
$ pacman -Syu
$ pacman -S mingw-w64-x86_64-go
$ pacman -S git
```
- `-Syu`で失敗する場合は下記を実行してから再実行する
  - 参考：https://o-murasaki.liblo.jp/archives/7057256.html
```
wget http://repo.msys2.org/msys/x86_64/msys2-keyring-r21.b39fb11-1-any.pkg.tar.xz
wget http://repo.msys2.org/msys/x86_64/msys2-keyring-r21.b39fb11-1-any.pkg.tar.xz.sig
pacman-key --verify msys2-keyring-r21.b39fb11-1-any.pkg.tar.xz{.sig,}
pacman -U --config <(echo) msys2-keyring-r21.b39fb11-1-any.pkg.tar.xz
pacman -U msys2-keyring-r21.b39fb11-1-any.pkg.tar.xz
```

- 以下を`C:\byod\MSYS2Portable\App\msys32\etc\bash.bashrc` の末尾に追加
  - GOPATHにgo installしたものがbuildされる
```
export PATH=$PATH:/c/oit/byod/MSYS2Portable/App/msys32/mingw64/lib/go/bin
export GOPATH=C:\oit\byod\MSYS2Portable\App\msys32\mingw64\lib\go
```
- icon作成環境の導入
  - .icoファイルを用意してrsrcをbuild
    - 参考：http://blog.y-yuki.net/entry/2017/04/22/000000
    
```
$ go install -v github.com/akavel/rsrc@latest
```

## ICOファイルの作成
- http://flat-icon-design.com/
  - このあたりで透過PNG画像をDLし，↓のサイトでICOファイルに変換する
- https://service.tree-web.net/icon_converter/
- PowerpointのエクスポートでPNG出力して，Irfanviewで透過させるしかないかなー


## ビルド
- ↑でビルドしたrsrc.exeを*.goファイルと同じディレクトリに置き，msys2の環境で `build_javatpl-bash.sh`を実行する
  - `sh build_javatpl-bash.sh`
- このとき，`javatpl-bash.go` のL15をPortableGitのバージョンに合わせて修正すること
