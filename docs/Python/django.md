Forms
=====

### Formsets

Create a simple model with few fields.

``` {.python}
# models.py

class Employee(models.Model):

    name = models.CharField(max_length=40)
    is_manager = models.BooleanField(default=False)
    email = models.CharField(max_length = 100)

    def __str__(self):
        return self.name
```

After creating a model, lets create a model form for that model. Also
create the formset for that model and form as shown below.

``` {.python}
# forms.py

from django import forms
from .models import *
from django.forms import modelformset_factory


class EmployeeForm(forms.ModelForm):
    email = forms.EmailField(disabled=True)  # disable field

    class Meta:
        model = Employee
        fields = ['email', 'name', 'is_manager']


EmployeeFormSet = modelformset_factory(Employee, form=EmployeeForm, max_num=0)
```

Use the formset created in the view.

``` {.python}
# views.py

from django.shortcuts import render
from .forms import *
from django.views import View
from .models import *


def index(request):
    context = {}
    if request.method == 'GET':

        formset = EmployeeFormSet()
        return render(request, 'tryformsets/index.html', {'formset' : formset})

    if request.method == 'POST':

        # formset = EmployeeFormSet(request.POST or None, request.FILES or None)
        formset = EmployeeFormSet(request.POST)
        if formset.is_valid():
            formset.save()
        formset = EmployeeFormSet()
        return render(request, 'tryformsets/index.html', {'formset' : formset})
```

Then create the template and use the formset. Make sure to include the
`{{formset.management_form}}` else it gives error. For more information
check the
[link](https://docs.djangoproject.com/en/2.2/topics/forms/formsets/#understanding-the-managementform)

``` {.example}
<form method="POST" action=".">{% csrf_token %}

    {{ formset.management_form }}

    <table class="table">
        <thead>
            <tr>
                <th>Name</th>
                <th>Item Name</th>
                <th>Item Price</th>
            </tr>
        </thead>
        {% for form in formset %}

        <tbody>
            {{ form.id }}
            <tr>
                <td>{{ form.name }}</td>
                <td>{{ form.email }}</td>
                <td>{{ form.is_manager }}</td>
            </tr>
            {% endfor %}
        </tbody>

    </table>
    <button type="submit">Submit</button>
</form>
```

<hr>

Working with DB
---------------

### Bulk Update

``` {.python}
# Coverage is a model which we need to update in bulk.
# Here we are trying to update the rows with pk = 33, 34 and 35 with different values.
>>> objs = []
>>> objs.append(Coverage.objects.get(pk = 33))                                             
>>>                                                                                        
>>> objs.append(Coverage.objects.get(pk = 34))                                             
>>> objs.append(Coverage.objects.get(pk = 35))                                             
>>>                                                                                        
>>> objs[1].coverage_needed = True                                                         
>>>                                                                                        
>>> objs[1].coverage_needed                                                                
True                                                                                       
>>>                                                                                        
>>> objs[2].coverage_needed                                                                
False                                                                                      
>>>                                                                                        
>>> objs[0].unavailable = True                                                             
>>>                                                                                        
>>> objs[2].supply_called = True                                                           
>>>                                                                                        
>>> Coverage.objects.bulk_update(objs, ['coverage_needed', 'supply_called', 'unavailable'])
```

Links
-----

### Forms

-   [DataTable
    Editable](https://stackoverflow.com/questions/56290703/django-edit-html-table-rows-and-update-database)
-   [Django Forms -
    HTML](https://stackoverflow.com/questions/39183155/django-with-html-forms)
-   [Django Forms - Rendering each form element manually for better
    styling.](https://simpleisbetterthancomplex.com/article/2017/08/19/how-to-render-django-form-manually.html)
