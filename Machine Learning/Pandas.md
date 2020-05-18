# Basics

__Extracting a Series vs DataFrame from a Dataframe__

```python
df = pd.read_csv('housing.csv')
ocean_prox = df['ocean_proximity']      # Gives you a series
ocean_prox = df[['ocean_proximity']]    # Gives you a dataframe
```

__Correlation__

```python
df.corr()
```

# Data into Bins



```python
pd.cut(df["median_income"],4)  # make 4 bins from median_income. You can also explicitly give the bin start and end point.
```