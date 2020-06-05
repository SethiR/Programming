# Linux

## File structure

Google : "Linx Filesystem Hierarchy Standard" to get the big pdf which explains the file system of a linux system. However its summarized in 3.2 Requirements section as to which root directory is for what purpose.

The root system starts at `/`. Below listed are some of the important dirs and their uses.

- boot - files which boots your system. (It also has files which the grub bootloader reads)
- dev - files that represent devices. e.g. HardDrive etc...
- etc - configuration directory. (System and applications) When you remove a package via `apt remove` it does not remove the configuration files for that package. You can use `--purge` to remove the configurations files from `/etc/`
- lib/lib64 - library files e.g. packages or shared libraries
- mnt/media - you attach storage media to the system. (Could be a hadoop cluster or a flash drive). Where media is more removable but mnt is for network related.
- proc - process system related files
- root - root user does not a dir in `/home`. It stores in `/root`
- tmp - where temporary files live
- var - There are various dirs in it but check out `/var/log`. The linux system pretty much logs everything and you can find some details here. (Mostly used for trouble shooting)


### Env variables

Check this [link](https://www.tecmint.com/set-unset-environment-variables-in-linux/) for details.

- `.bashrc` - This file is user specific file that gets loaded each time user creates a new local session i.e. in simple words, opens a new terminal. All environment variables created in this file would take effect every time a new local session is started.
- `.bash_profile` - This file is user specific remote login file. Environment variables listed in this file are invoked every time the user is logged in remotely i.e. using ssh session. If this file is not present, system looks for either .bash_login or .profile files.
- `/etc/envirounment` - This file is system wide file for creating, editing or removing any environment variables. Environment variables created in this file are accessible all throughout the system, by each and every user, both locally and remotely.
- `/etc/bash.bashrc` - System wide bashrc file. This file is loaded once for every user, each time that user opens a local terminal session. Environment variables created in this file are accessible for all users but only through local terminal session. When any user on that machine is accessed remotely via a remote login session, these variables would not be visible.
- `/etc/profile` - System wide profile file. All the variables created in this file are accessible by every user on the system, but only if that user’s session is invoked remotely, i.e. via remote login. Any variable in this file will not be accessible for local login session i.e. when user opens a new terminal on his local system.


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

Search for a given string in a file or content in a file.

```sh
grep "@" lori.christmas.txt
```

-r is recursive.
```sh
grep -r "error" /var/log/syslog
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

Total 9 charaters govern the permissions (apart from the 1 on the left which tells us that whether its a direcotry (d) file (-) or a symlink (l)). The permissions are divided into 3 of 3 groups (total 9) with user, group and others in that order.

- First 3 - (Read, Write, Execute) - for user.
- Next 3 - (Read, Write, Execute) - for group.
- Last 3 - (Read, Write, Execute) - for others (eveyone else).

Each operation has a number assigned to it.

- Read = 4
- Write = 2
- Execute = 1

The below command gives all permissions to everyone.
```sh
chown 777 abc.txt
```

Note : If you do not have execute on a directory then you cannot cd into it. i.e. you cannot make it your current working directory.

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


__su__

You can switch user using the `su` command.

e.g.

```sh
su hadoop
```

or if you wish to login as root (not you executing as root priviledges)

```sh
sudo su -
```

---

__systemctl__

Use `systemctl` command to check system services. Some of the options are listed below.

- start
- stop
- status
- disable - when the server restarts this service will not auto run
- enable - when the server restarts this service will auto run

---

__history__

The `history` command gives you the list of all commands you have executed. You can also grep through it as shoown below

```sh
history | grep apt
```

The history command output -- each command is associated with a number. You can reexeucte that command using the `!number` as shown below 

```sh
!162
```

---

__head tail__

The cat command shows you the whole file, but head or tail and show you (by default 10) first or last lines. You can customize it with `-n 100` to see 100 (first or last) lines.

You can also use `-f` to follow a file. (This will not return you the prompt back but will update your terminal window with new lines added to this file so its like a live debugger)

For example lets follow the auth log file
```sh
tail -f /var/log/auth.log
```

---

__journalctl__

This is (a upcoming) command where you can check out the logs of a particular service.

```sh
journalctl -u apache2
```

To follow logs

```sh
journalctl -fu apache2
```

---

__df__

Disk usage command. Tack on -h to get human readable output (also you can do that with ls e.g. `ls -lh`)


```sh
df -h
```

Gives details of that dir and sub dir.
```sh
du -hsc /home/*
```

You can also use package `ncdu` to get more detail information about the file system disk usage.

---

__find__

File any file which is in that dir and has name ending in .log Use (-iname) for case insensitive.
```sh
find /var/log -name "*.log"
```
! acts as a negation. Find which does not end in .log
```sh
find /var/log ! -name "*.log"
```

The find command by default does not distinguish b/w files and directory. You can use the `-type d` for dirs and `-type f` for files.


```sh
find /etc -type d -name "x*"
```

Find files which were modified in certain duration (days)
```sh
sudo find /home/rs -mtime 1
```

Find all markdown files which were modified within last 3 mins
```sh
find /home/rs -cmin -3 -name "*.md"
```

Find command gets advanced and here is a good example below. Find all dirs in the current dir, where permission is not 755 and execute chmod on those dirs (and not files) to make them 755

```sh
find . -type d ! -perm 755 -exec chmod 755 {} \;
```

---


## Misc topics

### Checking the logs

Check out the `/var/logs/auth*` file to check out. This file logs like package installs, login failures, sudo access and doing stuff etc... and if you see that someone did something you can go to their history file and check out what command they ran.

Check out the `syslog` file in the same place. It also logs all types of things the system does. (e.g. start service, schedule tasks, configuration failures for applications)

Check out hte `apt` dir in `/var/logs` to check out all apt related logs. (only for deb based system which use apt, other distros may have something different e.g. yum or arch equivalent)

### Alias

You can create your own shorthand for long commands.

```sh
alias cb="vim ~/.bashrc"
```
When you create these aliases, they are not stored on the disk and are lost when you close the shell. If you wish to store them you need to store them in your .bashrc file.

### Working with streams

There are total 3 types of streams

- Standard input - Assigned 0
- Standard output - Assigned 1
- Standard error - Assigned 2

Any output which is displayed on the terminal is part of the standard output `stdout`.

E.g. take the output produced by the command and save in hello.txt. (As you see 1 is for standard output)

```sh
echo "Hello World" 1> hello.txt
```

Another example - When you run a find command some of the dirs you will not have access to. So lets say you only wish to see the dirs which do not result in error, then you can send the error entries to `/dev/null` which is like a black hole in linux. Anything which is sent there (output or file) is effectively deleted or lost.

```sh
find / -name "log" 2> /dev/null
    /run/log
    /usr/src/linux-headers-5.4.0-33-generic/include/config/dm/log
    /usr/src/linux-headers-5.4.0-33-generic/include/config/nf/log
    /usr/src/linux-headers-5.4.0-33-generic/include/config/log
    /usr/src/linux-headers-5.4.0-33-generic/include/config/printk/safe/log
    /sys/module/vboxguest/parameters/log
```


Find all log files and redirect standard output `>` (by default its assumed to be 1 if not specified) and also send standard error (2) to standard output(1). So results.txt will contain dirs which you have access to and dirs which you do not have access to.
```sh
find / -name "log" > results.txt 2>&1
```


Another example : push standard output first (files in all dirs which you have access to) and then append standard error (all dirs which you do not have access to) in results.txt

```sh
find / -name "log" > results.txt 2>>results.txt
```

A quick example of standard input command.

```sh
cat < results.txt
```

### Background and foreground


- Exit long sunning process - `Ctrl + z`
- Get back to long running progress - `fg`
- To check applications in backgound run `job` command

## References
- [Linx man pages](https://www.kernel.org/doc/man-pages/)
- [50 most used commands](https://www.thegeekstuff.com/2010/11/50-linux-commands/)