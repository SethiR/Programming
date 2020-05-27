# Context Managers

The `with` statement sets up a temporary context and reliably tears it
down. The `with` statement was designed to simplify the try/finally
pattern which gaureentees even if the block is aborted even because of
an exception the finally clause will release the critical resource.

The context manager works with `__enter__` and `__exit__` protocol.

**Usage**

The context manager should be used for common setup and tear down code,
which is not just applicable for files. There can be various uses for
it.

```python
# Sample context manager for file.

class File:

    def __init__(self, file, method):
        self.file = open(file, method)

    def __enter__(self):
        return self.file  # returns the object which will sit in "f" --> with File(abc.txt, w) as "f"

    def __exit__(self, exception_type, exception_value, traceback):   # The exit must take in these 4 params else it raises exception
        self.file.close()
```

The context manager which you see above provides a pattern of
`__enter__` and `__exit__`. However if you see there is no exception
which is handled here. One way is to handle exceptionions manually...

```python
# Another example of a context manager.

import time

class Timer:

    def __enter__(self):
        self.start = time.time_ns()
        return self

    def __exit__(self, exception_type, exception_value, traceback):
        self.end = time.time_ns()
        self.processing_time = self.end - self.start

```

```python
with Timer() as t:
    time.sleep(.1)
    for i in range(10):
        print(i)

t.processing_time
```

```python
0
1
2
3
4
5
6
7
8
9





100219600
```

---

## contextmanager

Handling all sorts of exceptions can be difficult so python has provided
with a decorator that turns a generator function into a context manager.
Because think of it `__enter__` is starting something and pausing and
then `__exit__` takes over when we have to tear it down or finish the
work which is exactly what a generator does it yeilds and waits.

You can use the `contextlib` library to import the `contextmanager`
decorator to turn your generator into a context manager.

Go and check out the code of `contextmanager` it does handle a lot of
exceptions and makes it easier to create a context manager and do away
with the `__enter__` and `__exit__` protocol.

```python
from contextlib import contextmanager
import time

@contextmanager
def timer():
    start = time.time_ns()
    yield  # same concept it should return something which goes into the with "as" variable.
    end = time.time_ns()
    print(f"Time taken in ns = {end - start}")

with timer() as t:
    time.sleep(.1)
    for i in range(10):
        print(i)
```

```python
0
1
2
3
4
5
6
7
8
9
Time taken in ns = 100953200
```

```python
@contextmanager
def file_manager(f):
    file = open(f)
    yield  file # same concept it should return something which goes into the with "as" variable.
    file.close()
    print("File closed")
```

```python
with file_manager("django.md") as f:
    print(f.readlines())
```

```sh
['# Forms\n', '\n', '### Formsets\n', '\n', '\n', 'Create a simple model with few fields.\n', '\n', '```python\n', '# models.py\n', '\n', 'class Employee(models.Model):\n', '\n', '    name = models.CharField(max_length=40)\n', '    is_manager = models.BooleanField(default=False)\n', '    email = models.CharField(max_length = 100)\n', '\n', '    def __str__(self):\n', '        return self.name\n', '```\n', '\n', 'After creating a model, lets create a model form for that model. Also create the formset for that model and form as shown below.\n', '\n', '```python\n', '# forms.py\n', '\n', 'from django import forms\n', 'from .models import *\n', 'from django.forms import modelformset_factory\n', '\n', '\n', 'class EmployeeForm(forms.ModelForm):\n', '    email = forms.EmailField(disabled=True)  # disable field\n', '\n', '    class Meta:\n', '        model = Employee\n', "        fields = ['email', 'name', 'is_manager']\n", '\n', '\n', 'EmployeeFormSet = modelformset_factory(Employee, form=EmployeeForm, max_num=0)\n', '\n', '```\n', '\n', 'Use the formset created in the view.\n', '\n', '```python\n', '# views.py\n', '\n', 'from django.shortcuts import render\n', 'from .forms import *\n', 'from django.views import View\n', 'from .models import *\n', '\n', '\n', 'def index(request):\n', '    context = {}\n', "    if request.method == 'GET':\n", '        \n', '        formset = EmployeeFormSet()\n', "        return render(request, 'tryformsets/index.html', {'formset' : formset})\n", '\n', "    if request.method == 'POST':\n", '\n', '        # formset = EmployeeFormSet(request.POST or None, request.FILES or None)\n', '        formset = EmployeeFormSet(request.POST)\n', '        if formset.is_valid():\n', '            formset.save()\n', '        formset = EmployeeFormSet()\n', "        return render(request, 'tryformsets/index.html', {'formset' : formset})\n", '\n', '```  \n', '\n', 'Then create the template and use the formset. Make sure to include the `{{formset.management_form}}` else it gives error. For more information check the [link](https://docs.djangoproject.com/en/2.2/topics/forms/formsets/#understanding-the-managementform)\n', '\n', '```html\n', '<form method="POST" action=".">{% csrf_token %}\n', '\n', '    {{ formset.management_form }}\n', '\n', '    <table class="table">\n', '        <thead>\n', '            <tr>\n', '                <th>Name</th>\n', '                <th>Item Name</th>\n', '                <th>Item Price</th>\n', '            </tr>\n', '        </thead>\n', '        {% for form in formset %}\n', '\n', '        <tbody>\n', '            {{ form.id }}\n', '            <tr>\n', '                <td>{{ form.name }}</td>\n', '                <td>{{ form.email }}</td>\n', '                <td>{{ form.is_manager }}</td>\n', '            </tr>\n', '            {% endfor %}\n', '        </tbody>\n', '\n', '    </table>\n', '    <button type="submit">Submit</button>\n', '</form>\n', '```\n', '\n', '<hr>\n', '\n', '## Working with DB\n', '\n', '### Bulk Update\n', '\n', '```python\n', '# Coverage is a model which we need to update in bulk.\n', '# Here we are trying to update the rows with pk = 33, 34 and 35 with different values.\n', '>>> objs = []\n', '>>> objs.append(Coverage.objects.get(pk = 33))                                             \n', '>>>                                                                                        \n', '>>> objs.append(Coverage.objects.get(pk = 34))                                             \n', '>>> objs.append(Coverage.objects.get(pk = 35))                                             \n', '>>>                                                                                        \n', '>>> objs[1].coverage_needed = True                                                         \n', '>>>                                                                                        \n', '>>> objs[1].coverage_needed                                                                \n', 'True                                                                                       \n', '>>>                                                                                        \n', '>>> objs[2].coverage_needed                                                                \n', 'False                                                                                      \n', '>>>                                                                                        \n', '>>> objs[0].unavailable = True                                                             \n', '>>>                                                                                        \n', '>>> objs[2].supply_called = True                                                           \n', '>>>                                                                                        \n', ">>> Coverage.objects.bulk_update(objs, ['coverage_needed', 'supply_called', 'unavailable'])\n", '```\n', '\n', '## Links\n', '\n', '### Forms\n', '\n', '- [DataTable Editable](https://stackoverflow.com/questions/56290703/django-edit-html-table-rows-and-update-database)\n', '- [Django Forms - HTML](https://stackoverflow.com/questions/39183155/django-with-html-forms)\n', '- [Django Forms - Rendering each form element manually for better styling.](https://simpleisbetterthancomplex.com/article/2017/08/19/how-to-render-django-form-manually.html)\n', '\n', '\n', '\n']
File closed
```

There could be certain cases where you need to handle and tackle
excpetions manually or have some explicit requirements which are better
suited in class then you can go ahead and use the traditional way.
