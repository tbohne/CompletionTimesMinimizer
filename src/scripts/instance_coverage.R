library(ggplot2)
library(plyr)

input <- read.csv(file = "tim_results1.csv", header = TRUE, sep = ",")
numOfInstances <- 12

print("########## THE ACTUAL RESULTS ############")
print(count(input, vars = "prec_model"))
print("##########################################")

paste("m1: ", sum(input == "m1"), " solutions")
paste("m2: ", sum(input == "m2"), " solutions")
paste("m3: ", sum(input == "m3"), " solutions")
paste("m4: ", sum(input == "m4"), " solutions")

SOLUTIONS <- c(
    sum(input == "m1") / numOfInstances * 100,
    sum(input == "m2") / numOfInstances * 100,
    sum(input == "m3") / numOfInstances * 100,
    sum(input == "m4") / numOfInstances * 100
)
PREC_MODEL <- c("m1", "m2", "m3", "m4")

png(file = "solver_instance_coverage.png")

# plot the bar chart
barplot(SOLUTIONS, names.arg = PREC_MODEL, xlab = "prec_model", ylab = "instance coverage (%)", col = c(rgb(32, 43, 50, maxColorValue = 255)), border = "black")

# save the file
dev.off()
