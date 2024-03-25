package CustomerTableCellReander;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CustomerTableCellRenderer extends DefaultTableCellRenderer {


    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        Component cellComponent1 = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,row,3);

        // Assuming the "Available Items" column is at index 3
        Object value1 = table.getValueAt(row, 3);
        int availableItems = (value1 != null) ? Integer.parseInt(value1.toString()) : 0;

        // Check if available items are less than 3, and set the background color for the entire row to red
        if (availableItems < 3 && availableItems>0) {
            cellComponent.setBackground(Color.RED);
            cellComponent.setForeground(Color.WHITE); // Optional: set text color to make it readable
        } else {
            // Reset background color for other rows
            cellComponent.setBackground(table.getBackground());
            cellComponent.setForeground(table.getForeground());
        }

        return cellComponent;
    }
}
