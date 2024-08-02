package com.iskeru.splitwise.expenses.model;

import com.iskeru.splitwise.groups.model.SplitWiseGroup;
import com.iskeru.splitwise.users.model.SplitWiseUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SplitWiseCreateExpenseTest {
    public static final BigDecimal TWO = new BigDecimal("2").setScale(2);
    public static final BigDecimal FIVE = new BigDecimal("5").setScale(2);
    public static final BigDecimal SIX = new BigDecimal("6").setScale(2);

    @Mock
    SplitWiseUser user;

    @Mock
    SplitWiseUser otherUser;

    @Mock
    SplitWiseUser anotherUser;

    @Mock
    SplitWiseGroup group;

    @Test
    public void pairGroupExpenseShare() {
        buildPair();

        List<SplitWiseExpenseUser> results = SplitWiseCreateExpense.buildUsersFromGroupEqually(user, group, BigDecimal.TEN.negate().setScale(2));

        assertEquals(2, results.size());
        assertEquals(BigDecimal.TEN.setScale(2), results.get(0).getPaidShare(), "User paid fully");
        assertEquals(FIVE, results.get(0).getOwedShare(), "User owns half");
        assertEquals(BigDecimal.ZERO, results.get(1).getPaidShare(), "Other User paid zero");
        assertEquals(FIVE, results.get(1).getOwedShare(), "Other User owns half");
    }

    @Test
    public void pairGroupRefundShare() {
        buildPair();

        List<SplitWiseExpenseUser> results = SplitWiseCreateExpense.buildUsersFromGroupEqually(user, group, BigDecimal.TEN.setScale(2));

        assertEquals(2, results.size());
        assertEquals(FIVE, results.get(0).getPaidShare(), "User paid fully");
        assertEquals(BigDecimal.TEN.setScale(2), results.get(0).getOwedShare(), "User owns half");
        assertEquals(FIVE, results.get(1).getPaidShare(), "Other User paid zero");
        assertEquals(BigDecimal.ZERO, results.get(1).getOwedShare(), "Other User owns half");
    }

    @Test
    public void trioGroupExpenseShare() {
        buildTrio();

        List<SplitWiseExpenseUser> results = SplitWiseCreateExpense.buildUsersFromGroupEqually(user, group, SIX.negate());

        assertEquals(3, results.size());

        assertEquals(SIX, results.get(0).getPaidShare(), "User paid fully");
        assertEquals(TWO, results.get(0).getOwedShare(), "User owns half");

        assertEquals(BigDecimal.ZERO, results.get(1).getPaidShare(), "Other User paid zero");
        assertEquals(TWO, results.get(1).getOwedShare(), "Other User owns half");

        assertEquals(BigDecimal.ZERO, results.get(2).getPaidShare(), "Another User paid zero");
        assertEquals(TWO, results.get(2).getOwedShare(), "Another User owns half");
    }

    @Test
    public void trioGroupRefundShare() {
        buildTrio();

        List<SplitWiseExpenseUser> results = SplitWiseCreateExpense.buildUsersFromGroupEqually(user, group, SIX);

        assertEquals(3, results.size());

        assertEquals(TWO, results.get(0).getPaidShare(), "User paid fully");
        assertEquals(SIX, results.get(0).getOwedShare(), "User owns half");

        assertEquals(TWO, results.get(1).getPaidShare(), "Other User paid zero");
        assertEquals(BigDecimal.ZERO, results.get(1).getOwedShare(), "Other User owns half");

        assertEquals(TWO, results.get(2).getPaidShare(), "Another User paid zero");
        assertEquals(BigDecimal.ZERO, results.get(2).getOwedShare(), "Another User owns half");
    }

    private void buildPair() {
        Mockito.doReturn(1L).when(user).getId();
        Mockito.doReturn(2L).when(otherUser).getId();

        Mockito.doReturn(Arrays.asList(user, otherUser)).when(group).getMembers();
    }

    private void buildTrio() {
        Mockito.doReturn(1L).when(user).getId();
        Mockito.doReturn(2L).when(otherUser).getId();
        Mockito.doReturn(2L).when(anotherUser).getId();

        Mockito.doReturn(Arrays.asList(user, otherUser, anotherUser)).when(group).getMembers();
    }
}