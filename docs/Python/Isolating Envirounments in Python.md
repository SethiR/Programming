# Isolating Envirounments in Python

Sometimes pip is not install on linux based system even if python is
installed. In these cases you can install pip for python3 by using the
following command.

```sh
sudo apt install python3-pip
```

Getting details of pip.

```sh
pip3 show pip
```

Installing system-wide packages directly from PyPI is not recommended,
and should be avoided.

The dependencies should be isolated. There are 2 types of isolations -
Application level isolation - Venv - System level isolation - VMWare,
virtualbox, docker

---

Using `venv`

Create a python envirounment using `venv`

```sh
python3 -m venv ENV
```

This will create a folder name ENV which will store the python
envirounment.

You can activate it using the following command.

```sh
source ENV/bin/activate
```

After this your prompt will change `(ENV) ➜  ENV` which means you have
activate it.

Once you install packages using pip3 in your local ENV you can see the
packages installed using the following command. `pip3 freeze`

To store the list of packages in a requirements.txt file so that the
envirounment can be created again on another machine if required.

Pipe the output of `pip3 freeze` into that text file.

```sh
pip3 freeze > requirements.txt
```

Your text file will look something like below stating each package
installed on that env. Remember `pip3 freeze` does not know which
package is being used or not. It just compiles a list of packages
installed.

```sh
click==7.1.2
Flask==1.1.2
itsdangerous==1.1.0
Jinja2==2.11.2
MarkupSafe==1.1.1
Werkzeug==1.0.1
```

To create the same envirounment you can use the command.

```sh
pip3 install -r requirements.txt
```

You can also deactivate using that python implementation by using
command `pip3 freeze`