tennis_data<-read.csv("2018.csv", header=TRUE, fill=TRUE, sep=",")

tennis_data

which(tennis_data$WRank>10)

tennis_data$WRank=as.integer(tennis_data$WRank)
tennis_data$LRank=as.integer(tennis_data$LRank)