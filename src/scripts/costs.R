library(ggplot2)

input <- read.csv(file = "../../res/solutions.csv", header = TRUE, sep = ",")
solverEntries <- subset(input, prec_model == "m1" | prec_model == "m2" | prec_model == "m3" | prec_model == "m4")

plotPointsPre <- ggplot(data = solverEntries, aes(x = obj, y = instance, color = prec_model, group = prec_model))
finalPlot <- plotPointsPre + geom_point() + xlab("obj_val") + ylab("instance")

ggsave(finalPlot, file = "obj_values.png", width=6, height=4)

# LPTData <- subset(input, solver == "LPT")
# LPTCosts <- subset(LPTData, select = c(obj))
# paste("avg costs of LPT: ", round(mean(as.numeric(as.character(LPTCosts[["obj"]]))), digits = 2))

# SPSData <- subset(input, solver == "SPS")
# SPSCosts <- subset(SPSData, select = c(obj))
# paste("avg costs of SPS: ", round(mean(as.numeric(as.character(SPSCosts[["obj"]]))), digits = 2))

# CPLEXData <- subset(input, solver == "SPS")
# CPLEXCosts <- subset(CPLEXData, select = c(obj))
# paste("avg costs of CPLEX: ", round(mean(as.numeric(as.character(CPLEXCosts[["obj"]]))), digits = 2))

# TSData <- subset(input, solver == "TS")
# TSCosts <- subset(TSData, select = c(obj))
# paste("avg costs of TS: ", round(mean(as.numeric(as.character(TSCosts[["obj"]]))), digits = 2))
