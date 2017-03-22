function plotData(X, y)
%PLOTDATA Plots the data points X and y into a new figure 
%   PLOTDATA(x,y) plots the data points with + for the positive examples
%   and o for the negative examples. X is assumed to be a Mx2 matrix.

% Create New Figure
figure; hold on;

% ====================== YOUR CODE HERE ======================
% Instructions: Plot the positive and negative examples on a
%               2D plot, using the option 'k+' for the positive
%               examples and 'ko' for the negative examples.
%

#positivey = find(y);

#plen=length(positivey);
#nlen=length(y) - plen;

#negativey = zeros(nlen, 1);

#positiveX=zeros(plen, size(X)(2));
#negativeX=zeros(nlen, size(X)(2));

#px = 1;
#nx = 1;

#for i=1:length(y),
#  if (px <= length(positivey) && positivey(px) == i)
#    positiveX(px, :) = X(i, :);
#    px = px + 1;
#  else
#    negativeX(nx, :) = X(i, :);
#    nx = nx + 1;
#  endif
#end

#positivey = ones(plen, 1);
#plot(positiveX, positivey, 'k+');  hold on; plot(negativeX, negativey, 'ko');

% Find Indices of Positive and Negative Examples
pos = find(y==1); neg = find(y == 0);
% Plot Examples
plot(X(pos, 1), X(pos, 2), 'k+','LineWidth', 2, ...
     'MarkerSize', 7);
hold on; 
plot(X(neg, 1), X(neg, 2), 'ko', 'MarkerFaceColor', 'y', ...
     'MarkerSize', 7);

figure; 



% =========================================================================



hold off;

end
