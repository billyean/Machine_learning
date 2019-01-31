x_int <- 0
print(x_int)


if (c > 1) {
  print("greater")
}
x_real = 3.1415926535
print(x_real * x_real)

a=c(1,2,3)
x_vector = as.vector(a)
print(x_vector)
y_vector = as.vector(1:100)
print(sum(y_vector))
z_vector = y_vector * y_vector
print( z_vector)
print( z_vector > 50)

result = FALSE
if (!result) {
  print("result is FALSE")
} else {
  print("result is TRUE")
}

for (x in 1:10) {
  print(x)
}

l = 1
while(l <= 100) {
  cat("l =", l, "\n", sep = " ")
  l = l + 1
}

celsius_to_fahrenheit <- function(c) {
  return(c * 1.8 + 32)
}

print(celsius_to_fahrenheit(37))
print(celsius_to_fahrenheit(38))
print(celsius_to_fahrenheit(39))

fahrenheit_to_celsius <- function(f){
  return((f - 32) * 5 / 9.0)
}

print(fahrenheit_to_celsius(100))
