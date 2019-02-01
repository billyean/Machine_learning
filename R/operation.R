mtcars
str(mtcars)
summary(mtcars)
head(mtcars)
head(mtcars, n=1)
tail(mtcars, n=1)
View(mtcars)

seq(10, 1)
seq(1, 15, by=3)
req(0, 5)
subset(mtcars, mpg<15)
subset(mtcars, mpg<15 & wt<4)
which(mtcars$mpg<15)
mtcars[which(mtcars$mpg<15), ]