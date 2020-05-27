# Linux

## Useful commands

__Tar__

`.tar` file creates a single file bundling multiple files. It does not do compression like zip etc...


Creating a tar file
- `-c` create archive file
- `-f` lets you specify file name
- `-z` if you add z e.g. `-cfz` it will use gzip to compress the tar file.
```sh
tar -cf del.tar del
```

Opening up a tar file
```sh
tar -xvf del.tar
```

Viewing a tar file
```sh
tar tvf del.tar
```

---

__grep__

Search for a given string in a file.

```sh
grep "@" lori.christmas.txt
```

---

__rm__

Remove del dir and all its contents

```sh
rm -rf del/
```

Get info before deleting a file
```sh
rm -i filename.txt
```
---

__ssh__

Login to remote host

```sh
ssh user@ip -p port#
```

---

__sed__

Convert windows format to unix format file.

When you copy a DOS file to Unix, you could find \r\n in the end of each line. This example converts the DOS file format to Unix file format using sed command.
```sh
sed 's/.$//' filename
```

---

__pwd__

print working directory

```sh
pwd
```

---

__service__

Service command is used to run the system V init scripts. i.e Instead of calling the scripts located in the /etc/init.d/ directory with their full path, you can use the service command.

Check status
```sh
service ssh status
```

Check the status of all the services.
```sh
service --status-all
```

Restart a service.
```sh
service ssh restart
```

---

__cp__

Copy file1 to file2 preserving the mode, ownership and timestamp

```sh
cp -p file1 file2
```

---

__mv__

Move file

---

__chmod__

Change mode (permissions)

---

__chown__

Change ownership

---

__whereis__

When you want to find out where a specific Unix command exists (for example, where does ls command exists?), you can execute the following command.

```sh
whereis ls
```
---

__whatis__

Whatis command displays a single line description about a command.

---




## References
- [Linx man pages](https://www.kernel.org/doc/man-pages/)
- [50 most used commands](https://www.thegeekstuff.com/2010/11/50-linux-commands/)