# Linear Regression

implement Multi-class Classification and Neural Networks.

Submitted by: **Tristan Yan**

## User Stories

The following **required** functionality is complete:

* [X] lrCostFunction.m - Logistic regression cost function
* [X] oneVsAll.m - Train a one-vs-all multi-class classifier
* [X] predictOneVsAll.m - Predict using a one-vs-all multi-class classifier
* [X] predict.m - Neural network prediction function


## Video Walkthrough 

Here's a walkthrough of gradient descent for one variable:

<img src='./Number_DL.gif' title='Gradient Descent' width='' alt='Gradient Descent For One Variable' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## License

    Copyright [2017] [Tristan Yan]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


# Knowledge add here:

## g is a sigmoid function

* g(-30 + 10 * x1 + 10 * x2) ~ x1 And x2
* g(-30 + 20 * x1 + 20 * x2) ~ x1 Or x2
* g(10 - 20x) ~ Not x
* g(10 - 20 * x1 - 20 * x2 ) ~ (Not x1) And (Not x2)
