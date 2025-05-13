package pablo.charity_boxes.box;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pablo.charity_boxes.fundraising_event.FundraisingEvent;
import pablo.charity_boxes.fundraising_event.FundraisingEventRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Optional;
import pablo.charity_boxes.Currency;

@Service
public class BoxService {

    @Autowired
    private BoxRepository boxRepository;
    @Autowired
    private FundraisingEventRepository fundraisingEventRepository;

    public Box createBox(Box box) {

        Optional<Box> existingBox = boxRepository.findByName(box.getName());
        if (existingBox.isPresent()) {
            throw new IllegalStateException("Box with this name already exists");
        }

        return boxRepository.save(box);
    }

    public List<String> getAllBoxes() {
        List<Box> boxes = boxRepository.findAll();

        return boxes.stream().map(box -> {
            String name = box.getName();
            boolean assigned = box.getFundraisingEvent() != null;

            boolean empty = box.getAmounts() == null || box.getAmounts().values().stream()
                    .allMatch(amount -> amount == null || amount.compareTo(BigDecimal.ZERO) == 0);

            return String.format("Name: %s Assigned: %s Empty: %s", name, assigned, empty);
        }).collect(Collectors.toList());
    }

    public Box assignBoxToFundraisingEvent(String boxName, String eventName) {

        Optional<Box> boxOptional = boxRepository.findByName(boxName);
        if (boxOptional.isEmpty()) {
            throw new RuntimeException("Box with this name doesn't exist");
        }
        Box box = boxOptional.get();

        boolean isEmpty = box.getAmounts() == null || box.getAmounts().values().stream()
                .allMatch(amount -> amount == null || amount.compareTo(BigDecimal.ZERO) == 0);
        if (!isEmpty) {
            throw new IllegalStateException("Box is not empty");
        }

        Optional<FundraisingEvent> fundraisingEventOptional = fundraisingEventRepository.findByName(eventName);
        if (fundraisingEventOptional.isEmpty()) {
            throw new RuntimeException("FundraisingEvent not found");
        }
        FundraisingEvent fundraisingEvent = fundraisingEventOptional.get();

        box.setFundraisingEvent(fundraisingEvent);
        return boxRepository.save(box);
    }

    public Box addMoneyToBox(String boxName, BigDecimal amount, Currency currency) {

        if(!Currency.currencyExists(currency)){
            throw new RuntimeException("This currency isn't supported");
        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount must be a positive number");
        }

        Optional<Box> boxOptional = boxRepository.findByName(boxName);
        if (boxOptional.isEmpty()) {
            throw new RuntimeException("Box with this name doesn't exist");
        }
        Box box = boxOptional.get();

        box.getAmounts().putIfAbsent(currency, BigDecimal.ZERO);
        BigDecimal currentAmount = box.getAmounts().get(currency);
        BigDecimal newAmount = currentAmount.add(amount);
        box.getAmounts().put(currency, newAmount);

        return boxRepository.save(box);
    }

    @Transactional
    public Box emptyBox(String boxName) {
        Box box = boxRepository.findByName(boxName).orElseThrow(() -> new RuntimeException("Box with this name doesn't exist"));

        FundraisingEvent event = box.getFundraisingEvent();
        if (event == null) {
            throw new RuntimeException("Box is not assigned to any fundraising event");
        }

        Currency eventCurrency = event.getCurrency();
        Map<Currency, BigDecimal> amounts = box.getAmounts();

        BigDecimal totalMoneyInEventCurrency = BigDecimal.ZERO;
        for (Map.Entry<Currency, BigDecimal> entry : amounts.entrySet()) {
            Currency fromCurrency = entry.getKey();
            BigDecimal amount = entry.getValue() != null ? entry.getValue() : BigDecimal.ZERO;
            BigDecimal rate = Currency.getExchangeRate(fromCurrency, eventCurrency);
            BigDecimal converted = amount.multiply(rate);
            totalMoneyInEventCurrency = totalMoneyInEventCurrency.add(converted);
        }

        BigDecimal currentEventAmount = event.getAmount() != null ? event.getAmount() : BigDecimal.ZERO;
        event.setAmount(currentEventAmount.add(totalMoneyInEventCurrency));
        box.getAmounts().clear();

        return boxRepository.save(box);
    }

    @Transactional
    public void removeBox(String boxName) {
        Box box = boxRepository.findByName(boxName).orElseThrow(() -> new RuntimeException("Box with this name doesn't exist"));

        FundraisingEvent fundraisingEvent = box.getFundraisingEvent();
        if (fundraisingEvent != null) {
            fundraisingEvent.getBoxes().remove(box);
            box.setFundraisingEvent(null);
        }

        boxRepository.delete(box);
    }


}

