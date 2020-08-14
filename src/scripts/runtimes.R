library(ggplot2)
library(plyr)

input <- read.csv(file = "../../res/solutions.csv", header = TRUE, sep = ",")
solverEntries <- subset(input, prec_model == "m1" | prec_model == "m2" | prec_model == "m3" | prec_model == "m4")
plotPointsPre <- ggplot(data = solverEntries, aes(x = as.numeric(as.character(runtime)), y = instance, color = prec_model, group = prec_model))
finalPlot <- plotPointsPre + geom_point() + xlab("runtime (s)") + ylab("instance")
ggsave(finalPlot, file = "runtimes.png", width = 6, height = 4)

compute_avg_runtime <- function(model) {
    data <- subset(input, prec_model == model)
    runtime <- subset(data, select = c(runtime))
    return(round(mean(as.numeric(as.character(runtime[["runtime"]]))), digits = 2))
}

paste("avg runtime of m1: ", compute_avg_runtime("m1"))
paste("avg runtime of m2: ", compute_avg_runtime("m2"))
paste("avg runtime of m3: ", compute_avg_runtime("m3"))
paste("avg runtime of m4: ", compute_avg_runtime("m4"))
