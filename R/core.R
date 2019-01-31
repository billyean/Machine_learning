prime <- function(n) {
  if(n<0){
    FALSE
  }
  if(is.numeric(n)==FALSE) {
    return(FALSE)
  }
  if(x%%1!=0) {
    return(FALSE) 
  }
  if (n == 1) {
    return(FALSE)
  }
  if (n == 2) {
    return(TRUE)
  }
  for(i in 2:ceiling(sqrt(n))) {
    if (n %% i == 0) {
      return(FALSE)
    }
  }
  
  return(TRUE)
}

nums = 100000
prime_check=rep(0, nums)
cnt=rep(0, nums)
ratio=cnt

for(i in 1:nums) {
  prime_check[i]=prime(i)
  cnt[i]=sum(prime_check)
  ratio[i]=cnt[i]*log(i)/i
}

