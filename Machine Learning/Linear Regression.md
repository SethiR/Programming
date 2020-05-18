
# 1. Regularization

The regularization term should only be added to the model at the time of training. Once the model is trainined we should use the unregularized version to measure performance. If you are using any regularized model its important to scale data e.g. using `StandardScaler`.

It is almost always preferable to have at least a little bit of regularization, so generally you should avoid plain Linear Regression. Ridge is a good default, but if you suspect that only a few features are useful, you should prefer Lasso or Elastic Net because they tend to reduce the useless features.

## 1.1. Ridge Regression

Add a regularization term $\alpha\frac{1}{2}\sum_{1=1}^n \theta_i^2$ to the cost function $J(\theta)$. This forces the weights to be as small as possible. The hyperparameter $\alpha$ controls how much regularization is added. If its 0 then its just linear regression, if its too large then the weights will be almost equal to 0. Note that bias term $\theta_0$ is not regularized.

$$J(\theta) = MSE(\theta) + \alpha\frac{1}{2}\sum_{1=1}^n \theta_i^2 $$

Ridge regression can be performed using Gradient Descent or using closed form equation as given below.

$$\hat{\theta} = (X^\intercal X + \alpha A)^{-1} X^\intercal Y$$ 

- Uses L2 norm

## 1.2. Lasso Regression

- Least Absolute Shrinkage and Selection Operator Regression (usually simply called Lasso Regression)
- The lasso regression is almost similar to Ridge regression instead it uses L1 norm.
- An important characteristic of Lasso Regression is that it tends to eliminate the weights of the least important features (i.e., set them to zero).

## 1.3. Elastic Net Regression

Its a middle ground b/w Ridge and Lasso and the term is a combination of both, also you can set the ratio between the two using $r$ (note $r$ vs $1-r$).

$$ J(\theta) = MSE(\theta) + r \alpha \sum_{i=1}^n |\theta_i| + \frac{1-r}{2} \alpha \sum_{i=1}^n \theta^2_i$$

```python
elastic_net = ElasticNet(alpha=0.1, l1_ratio=0.5) # l1_ratio is r ratio
```