function [C, sigma] = dataset3Params(X, y, Xval, yval)
%EX6PARAMS returns your choice of C and sigma for Part 3 of the exercise
%where you select the optimal (C, sigma) learning parameters to use for SVM
%with RBF kernel
%   [C, sigma] = EX6PARAMS(X, y, Xval, yval) returns your choice of C and 
%   sigma. You should complete this function to return the optimal C and 
%   sigma based on a cross-validation set.
%

% You need to return the following variables correctly.
C = 1;
sigma = 0.3;

% ====================== YOUR CODE HERE ======================
% Instructions: Fill in this function to return the optimal C and sigma
%               learning parameters found using the cross validation set.
%               You can use svmPredict to predict the labels on the cross
%               validation set. For example, 
%                   predictions = svmPredict(model, Xval);
%               will return the predictions on the cross validation set.
%
%  Note: You can compute the prediction error using 
%        mean(double(predictions ~= yval))
%

%C_vect = [0.01; 1; 3];
%sigma_vect = [0.01; 1; 3];

C_vect = [0.01;0.03;0.1;0.3;1;3;10;30];
sigma_vect = [0.01;0.03;0.1;0.3;1;3;10;30];

C_length = length(C_vect);
sigma_length = length(sigma_vect);

error_master = zeros(C_length * sigma_length, 3);

for i = 1:C_length;
  for j = 1:sigma_length;
    cc = C_vect(i);
    sc = sigma_vect(j);
    model= svmTrain(X, y, cc, @(x1, x2) gaussianKernel(x1, x2, sc));
    predictions = svmPredict(model, Xval); 
    index = (i - 1)* sigma_length + j;
    error_master(index, 1) = cc;
    error_master(index, 2) = sc;
    error_master(index, 3) = mean(double(predictions ~= yval));
  end
end

[min_error, pos] = min(error_master(:,3));
C = error_master(pos, 1);
sigma = error_master(pos, 2);

% =========================================================================

end
