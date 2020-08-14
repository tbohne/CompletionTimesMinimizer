library(ggplot2)

input <- read.csv(file = "../../res/solutions.csv", header = TRUE, sep = ",")
solverEntries <- subset(input, prec_model == "m1" | prec_model == "m2" | prec_model == "m3" | prec_model == "m4")
plotPointsPre <- ggplot(data = solverEntries, aes(x = obj, y = instance, color = prec_model, group = prec_model))
finalPlot <- plotPointsPre + geom_point() + xlab("obj_val") + ylab("instance")
ggsave(finalPlot, file = "obj_values.png", width=6, height=4)

compute_avg_costs <- function(model) {
    data <- subset(input, prec_model == model)
    costs <- subset(data, select = c(obj))
    return(round(mean(as.numeric(as.character(costs[["obj"]]))), digits = 2))
}

paste("avg costs of m1: ", compute_avg_costs("m1"))
paste("avg costs of m2: ", compute_avg_costs("m2"))
paste("avg costs of m3: ", compute_avg_costs("m3"))
paste("avg costs of m4: ", compute_avg_costs("m4"))
