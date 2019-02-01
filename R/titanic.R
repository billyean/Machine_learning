Titanic

plot(Titanic)

str(Titanic)

my_titanic = as.data.frame(Titanic)
str(my_titanic)
subset(my_titanic, Survived=="Yes")
plot(my_titanic)
summary(my_titanic)
subset(my_titanic, Survived=="No")
pairs(my_titanic)

x=subset(my_titanic, Sex=="Male")
x
# for(i in 1:16) {
#   if(x$Survived[i]=="Yes"){
#     x$Survived[i]<-1
#   }else{
#     x$Survived[i]<-0
#   }
# }
x$Survived=as.integer(x$Survived)
x$Survived
Titanic[,,,2]
barplot(Titanic[1,,2,], col=c("blue","green"), legend=c("Male", "Female"), main="Titanic Survived 1st class")
barplot(Titanic[2,,2,], col=c("blue","green"), legend=c("Male", "Female"), main="Titanic Survived 2nd class")
barplot(Titanic[3,,2,], col=c("blue","green"), legend=c("Male", "Female"), main="Titanic Survived 3th class")
barplot(Titanic[4,,2,], col=c("blue","green"), legend=c("Male", "Female"), main="Titanic Survived crew")