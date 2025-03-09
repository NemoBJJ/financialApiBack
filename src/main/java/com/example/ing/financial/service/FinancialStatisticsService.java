package com.example.ing.financial.service;

import com.example.ing.financial.entity.TransactionIng;
import com.example.ing.financial.repository.TransactionIngRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FinancialStatisticsService {

    private final TransactionIngRepository transactionIngRepository;

    public FinancialStatisticsService(TransactionIngRepository transactionIngRepository) {
        this.transactionIngRepository = transactionIngRepository;
    }

    // Operações CRUD Básicas
    public List<TransactionIng> getAllTransactions() {
        return transactionIngRepository.findAll();
    }

    public TransactionIng addTransaction(TransactionIng transactionIng) {
        return transactionIngRepository.save(transactionIng);
    }

    public String deleteTransaction(Long id) {
        if (transactionIngRepository.existsById(id)) {
            transactionIngRepository.deleteById(id);
            return "Transaction removed successfully!";
        }
        return "Transaction not found!";
    }

    public List<TransactionIng> getTransactions() {
        return transactionIngRepository.findAll();
    }

    // Método de Cálculo de Estatísticas
    public FinancialStatistics calculateStatistics() {
        List<TransactionIng> transactions = transactionIngRepository.findAll();

        List<Double> revenues = transactions.stream()
                .filter(t -> "REVENUE".equalsIgnoreCase(t.getType()))
                .map(TransactionIng::getAmount)
                .collect(Collectors.toList());

        List<Double> expenses = transactions.stream()
                .filter(t -> "EXPENSE".equalsIgnoreCase(t.getType()))
                .map(TransactionIng::getAmount)
                .collect(Collectors.toList());

        double totalRevenues = roundToTwoDecimalPlaces(revenues.stream().mapToDouble(Double::doubleValue).sum());
        double totalExpenses = roundToTwoDecimalPlaces(expenses.stream().mapToDouble(Double::doubleValue).sum());
        double monthlyAverageRevenues = roundToTwoDecimalPlaces(totalRevenues / 12);
        double monthlyAverageExpenses = roundToTwoDecimalPlaces(totalExpenses / 12);
        double medianRevenues = roundToTwoDecimalPlaces(calculateMedian(revenues));
        double medianExpenses = roundToTwoDecimalPlaces(calculateMedian(expenses));
        double modeRevenues = roundToTwoDecimalPlaces(calculateMode(revenues));
        double modeExpenses = roundToTwoDecimalPlaces(calculateMode(expenses));
        double standardDeviationRevenues = roundToTwoDecimalPlaces(calculateStandardDeviation(revenues));
        double standardDeviationExpenses = roundToTwoDecimalPlaces(calculateStandardDeviation(expenses));

        return new FinancialStatistics(totalRevenues, totalExpenses, monthlyAverageRevenues, monthlyAverageExpenses,
                medianRevenues, medianExpenses, modeRevenues, modeExpenses, standardDeviationRevenues, standardDeviationExpenses);
    }

    // Métodos Auxiliares para Cálculos
    private double calculateMedian(List<Double> values) {
        if (values.isEmpty()) return 0;
        List<Double> sorted = values.stream().sorted().collect(Collectors.toList());
        int middle = sorted.size() / 2;
        if (sorted.size() % 2 == 0) {
            return (sorted.get(middle - 1) + sorted.get(middle)) / 2.0;
        } else {
            return sorted.get(middle);
        }
    }

    private double calculateMode(List<Double> values) {
        if (values.isEmpty()) return 0;
        return values.stream()
                .collect(Collectors.groupingBy(v -> v, Collectors.counting()))
                .entrySet()
                .stream()
                .max((entry1, entry2) -> Long.compare(entry1.getValue(), entry2.getValue()))
                .orElseThrow()
                .getKey();
    }

    private double calculateStandardDeviation(List<Double> values) {
        if (values.isEmpty()) return 0;
        double mean = values.stream().mapToDouble(Double::doubleValue).average().orElse(0);
        return Math.sqrt(values.stream().mapToDouble(v -> Math.pow(v - mean, 2)).average().orElse(0));
    }

    private double roundToTwoDecimalPlaces(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
