This file will contains most used features of SciKitLearn. I have tried to organize this file in the structure of Sklearn documentation i.e. Module wise. To check the official documentation check this [link](https://scikit-learn.org/stable/modules/classes.html).

__Links__

- [User Guide](https://scikit-learn.org/stable/user_guide.html)
- [API](https://scikit-learn.org/stable/modules/classes.html)

# Preprocessing

## Imputers

__Simple Imputer__ 

Transformers for missing value imputation, there are various strategies like, mean, median, constant value etc... . The `SimpleImputer` works on numerical values, so if our DataFrame has non numerical values like Strings, etc... we need to remove them before we feed the data into `SimpleImputer`. Example shown below...

```python
import pandas as pd
from sklearn.impute import SimpleImputer

df = pd.read_csv('housing.csv')
df_copy = df  # Keep a copy of DataFrame

# Lets drop the categorical values if any.
df.drop(["ocean_proximity"], axis = 1, inplace=True)

si = SimpleImputer(strategy = "mean")
si.fit(df)
transformed = si.transform(df)  # Now we have an nd-array of inputed values

# Create a DataFrame if required. Then you can add the categorical values back if required.
df = pd.DataFrame(transformed, columns = df.columns)
```

## Standard Scalar

Standardize features by removing the mean and scaling to unit variance. $z = \frac{(x - u)}{s}$ where _u_ is the mean and _s_ is the standard deviation. Centering and scaling happen independently on each feature. This scaler can also be applied to sparse CSR or CSC matrices by passing with_mean=False to avoid breaking the sparsity structure of the data.

```python
import pandas as pd
from sklearn.preprocessing import StandardScaler

df = pd.read_csv("housing.csv")
scalar = StandardScaler()
scalar.fit(df)
scalar.transform(df)
```

## OrdinalEncoder

Encode categorical features as an integer array.

The below example will encode all `ocean_proximity` into an array of `[1, 3, 0, 1, 2]`. Here you see we are converting a dataframe column into numpy arrays to do the encoding.

```python
enc = OrdinalEncoder()
e = enc.fit_transform(np.array(df['ocean_proximity']).reshape(-1,1))
```

You can also encode a column of Dataframe as shown below.

```python
ocean_prox = df[['ocean_proximity']]    # This gives you a df. ocean_prox is a 1 col df
oe = OrdinalEncoder()
ocean_prox_encoded = oe.fit_transform(ocean_prox)
```

The `OrdinalEncoder` preserves the categories `oe.categories_`.

If you wish to merge the encoded value  back to the Dataframe, you can include them as a new column as shown below.

```python
import pandas as pd
from sklearn.preprocessing import OrdinalEncoder

df = pd.read_csv('housing.csv')
oe = OrdinalEncoder()
ocean_prox = df[['ocean_proximity']]  # Creates a df
df['ocean_prox_encoded'] = oe.fit_transform(ocean_prox)  # Adding additional column
```

## 1.4. OneHotEncoder

There are other encoders avaiable as well such as `pd.dummy` which are easier to work with, however there are some considerable differences. Remember that these sklearn encoder(s) are classes and we create instance of these classes. Once they encode the values, they remeber which "String" maps to which interger value/matrix etc... thus they will perform the same transformation on training set, cross validation set, test set and in production. Thus it is important that we use these or override these when encoding values.

Thus we can handle any unknown data in production i.e. `handle_unknown = 'error'` or `handle_unknown = 'ignore'`

```python
import pandas as pd
from sklearn.preprocessing import OneHotEncoder

df = pd.read_csv('housing.csv')
ocean_prox = df[['ocean_proximity']]
oe = OneHotEncoder(sparse=False)   # This returns an array
df2 = pd.DataFrame(oe.fit_transform(ocean_prox), columns=oe.get_feature_names())    # Creating a new df based on feature names of ocean_prox col
df = pd.concat([df, df2], axis=1)
```

## 1.5. LabelBinarizer

Check this [Link](http://www.insightsbot.com/python-one-hot-encoding-with-scikit-learn/) to clarify b/w OrdinalEncoder, OneHotEncoder, LabelBinarizer


## 1.6. Custom Transformer

Sklearn provides many transformers but there could be possibilities to create custom transformation of data based on your scenario. The custom transformer should work with Sklearn pipelines thus implenting a function will not be as helpful.

You just need to implement the following methods.

- `fit()` return self
- `transform()`
- `fit_transform()` - get this free by simply adding TransformerMixin as a base class

A very simple example shown below ...

```python
"""
Custom Transformer for a Housing dataset.
The below is a very simple example, however we can do a lot with this.
The transform function can return a df, np.array (combined or not combined)
with existing.
"""

import pandas as pd
from sklearn.base import BaseEstimator, TransformerMixin

df = pd.read_csv('housing.csv')

class MyTransformer(BaseEstimator, TransformerMixin):
    
    def __init__(self):
        pass
    
    def fit(self):
        return self
    
    def transform(self, df):
        return df[['population']]**2
    

my_t = MyTransformer()
my_t.transform(df)  # Returns a new df with polynomial
```

## 1.7. Pipeline

The `sklearn.pipeline` module implements utilities to build a composite estimator, as a chain of transforms and estimators. Intermediate steps of the pipeline must be ‘transforms’, that is, they must implement fit and transform methods. The final estimator only needs to implement fit.

__`fit()` vs `fit_transform()` for the pipeline__

- When you call the pipeline’s `fit()` method, it calls `fit_transform()` sequentially on all transformers, passing the output of each call as the parameter to the next call until it reaches the final estimator, for which it calls the `fit()` method.
- `fit_transform()` transforms based on all of them.

```python
import pandas as pd
from sklearn.pipeline import Pipeline
from sklearn.preprocessing import StandardScaler
from sklearn.impute import SimpleImputer

df = pd.read_csv("housing.csv")
df_copy = df.drop('ocean_proximity', axis=1)  # Dropping categorical because of SimpleImputer & StandardScaler

# The pipeline
num_pipeline = Pipeline([
    ('imputer', SimpleImputer(strategy="median")),
    ('std_scaler', StandardScaler()),
])

# Using the pipeline on df_copy
num_pipeline_tr = num_pipeline.fit_transform(df_copy)
```

## 1.8. Polynomial Features

Generating polynomial features is easy as shown below.

```python
import numpy as np
from sklearn.preprocessing import PolynomialFeatures

X = np.random.random(4).reshape(-1,1)

```

---

# 2. Models

## 2.1. Linear  Models

### 2.1.1. Ridge Rigression

Add a regularization parameter.

```python
import pandas as pd
from sklearn.linear_model import Ridge

df = pd.read_csv('housing.csv')
df.dropna(inplace=True)

X = df.drop(['median_house_value', 'ocean_proximity'], axis=1)  # drop y and categorical. (Encode in actual)
y = df['median_house_value']

# ‘cholesky’ uses the standard scipy.linalg.solve function to obtain a closed-form solution.
model = Ridge(alpha = 1, solver = "cholesky") 
model.fit(X,y)
```

### 2.1.2. Lasso Rigression

```python
from sklearn.linear_model import Lasso
```

## 2.2. Ensemble Methods

### 2.2.1. Random Forest

```python
from sklearn.ensemble import RandomForestClassifier
clf = RandomForestClassifier(random_state=42)
result = cross_val_predict(clf, X_train, y_train_5, cv=3, method="predict_proba")
```

---

# 3. Model Selection

Sklearn [link](https://scikit-learn.org/stable/modules/classes.html#module-sklearn.model_selection)

## 3.1. Splitters

### 3.1.1. Train Test Split

Split arrays or matrices into random train and test subsets.

```python
from sklearn.model_selection import train_test_split
X, y = train_test_split(df, test_size = .2)  # Passing a df
```

- There is a paramter `stratify` which by default is `None`
- You can also set the `random_state` for repeatability.

### 3.1.2. StratifiedShuffleSplit

`StratifiedShuffleSplit` splits the data based on the percentage of category values. 

- You can state the number of splits which you want  e.g. `n_splits = 1`
- Also `random_state` for repeatability

Example below: - 

```python
import pandas as pd
from sklearn.model_selection import StratifiedShuffleSplit

df = pd.read_csv('housing.csv')
split = StratifiedShuffleSplit(n_splits=1, test_size=0.2, random_state=42)
for train_index, test_index in split.split(df, df["ocean_proximity"]):
    strat_train_set = df.loc[train_index]
    strat_test_set = df.loc[test_index]
```

StratifiedShuffleSplit returns the index of rows which you it has split, example below.

```python
>>> ss = StratifiedShuffleSplit(n_splits=2)
>>> split = ss.split(df, df['ocean_proximity'])
>>> list(split)

[(array([ 2766, 13808,  2658, ..., 12854, 11468,  8321], dtype=int64),
  array([12116, 11088,  1840, ..., 18747, 16266, 14731], dtype=int64)),
 (array([15490,  5435,  5497, ...,  8741,  1524, 11954], dtype=int64),
  array([16105,   282,  6746, ..., 10109, 17403, 18546], dtype=int64))]
```

## 3.2. Model Prediction

### 3.2.1. Cross Val Score

Evaluate a score by cross-validation, you can sore based on various [strategies](https://scikit-learn.org/stable/modules/model_evaluation.html#common-cases-predefined-values). 

```python
from sklearn.model_selection import cross_val_score
cross_val_score(sgd_clf, X_train, y_train_5, cv=3, scoring="accuracy")
```

### 3.2.2. Custom Cross Validation

You can run a loop while doing `StratifiedKFold` and fit a model on it and then get the score.

## 3.3. Hyper-parameter optimizers

### 3.3.1. Grid Search

Grid search runs all combinations of hyperparamters. The hyperparameters are different based on each model.

```python
from sklearn.model_selection import GridSearchCV

param_grid = [
	     {'n_estimators': [3, 10, 30], 'max_features': [2, 4, 6, 8]},
		 {'bootstrap': [False], 'n_estimators': [3, 10], 'max_features': [2, 3, 4]},
	]

forest_reg = RandomForestRegressor()

grid_search = GridSearchCV(forest_reg, param_grid, cv=5,
	scoring='neg_mean_squared_error',
	return_train_score=True)
```

`grid_search.cv_results_` returns the results, it can be transformed into a Pandas Dataframe to get a good look at the best hyperparamters for the model.

### 3.3.2. RandomizedSearchCV

Randomized search on hyper parameters.

---

# 4. Metrics

## 4.1. Precision Recall

```python
from sklearn.metrics import precision_score, recall_score
precision_score(y_train_5, y_train_pred)
recall_score(y_train_5, y_train_pred)
```

## 4.2. Confusion Matrix

```python
from sklearn.metrics import confusion_matrix
confusion_matrix(y_train_5, y_train_pred)
```

## 4.3. Area under curve - RoC

```python
from sklearn.metrics import roc_auc_score
roc_auc_score(y_train_5, y_scores)
```