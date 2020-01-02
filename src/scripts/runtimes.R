library(ggplot2)
library(plyr)

input <- read.csv(file = "../../res/solutions.csv", header = TRUE, sep = ",")

solverEntries <- subset(input, prec_model == "m1" | prec_model == "m2" | prec_model == "m3" | prec_model == "m4")
plotPointsPre <- ggplot(data = solverEntries, aes(x = as.numeric(as.character(runtime)), y = instance, color = prec_model, group = prec_model))
finalPlot <- plotPointsPre + geom_point() + xlab("runtime (s)") + ylab("instance")

ggsave(finalPlot, file = "runtimes.png", width = 6, height = 4)

# LPTData <- subset(input, prec_model == "LPT")
# LPTRuntime <- subset(LPTData, select = c(runtime))
# paste("avg runtime of LPT: ", round(mean(as.numeric(as.character(LPTRuntime[["runtime"]]))), digits = 2))

# SPSData <- subset(input, prec_model == "SPS")
# SPSRuntime <- subset(SPSData, select = c(runtime))
# paste("avg runtime of SPS: ", round(mean(as.numeric(as.character(SPSRuntime[["runtime"]]))), digits = 2))

# CPLEXData <- subset(input, prec_model == "CPLEX")
# CPLEXRuntime <- subset(CPLEXData, select = c(runtime))
# paste("avg runtime of CPLEX: ", round(mean(as.numeric(as.character(CPLEXRuntime[["runtime"]]))), digits = 2))

# TSData <- subset(input, prec_model == "TS")
# TSRuntime <- subset(TSData, select = c(runtime))
# paste("avg runtime of TS: ", round(mean(as.numeric(as.character(TSRuntime[["runtime"]]))), digits = 2))
