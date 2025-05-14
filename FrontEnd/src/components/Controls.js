// src/components/Controls.js
import React, { useState } from "react";
import { 
  addVendor, 
  addCustomer, 
  startSystem, 
  stopSystem 
} from "../services/ApiService";

const Controls = () => {
  const [vendorCount, setVendorCount] = useState(0);
  const [customerCount, setCustomerCount] = useState(0);
  const [customerQuantity, setCustomerQuantity] = useState(0);

  const handleAddVendor = async () => {
    try {
      await addVendor(vendorCount);
      alert(`${vendorCount} vendor(s) added successfully!`);
    } catch (error) {
      alert("Failed to add vendors");
    }
  };

  const handleAddCustomer = async () => {
    try {
      await addCustomer(customerCount, customerQuantity);
      alert(`${customerCount} customer(s) added successfully!`);
    } catch (error) {
      alert("Failed to add customers");
    }
  };

  const handleStartSystem = async () => {
    try {
      await startSystem();
      alert("System started successfully!");
    } catch (error) {
      alert("Failed to start system");
    }
  };

  const handleStopSystem = async () => {
    try {
      await stopSystem();
      alert("System stopped successfully!");
    } catch (error) {
      alert("Failed to stop system");
    }
  };

  return (
    <div className="controls">
      <h3>System Controls</h3>
      <div className="vendor-section">
        <h4>Add Vendors</h4>
        <input
          type="number"
          value={vendorCount}
          onChange={(e) => setVendorCount(Number(e.target.value))}
          placeholder="Number of Vendors"
        />
        <button onClick={handleAddVendor}>Add Vendors</button>
      </div>

      <div className="customer-section">
        <h4>Add Customers</h4>
        <input
          type="number"
          value={customerCount}
          onChange={(e) => setCustomerCount(Number(e.target.value))}
          placeholder="Number of Customers"
        />
        <input
          type="number"
          value={customerQuantity}
          onChange={(e) => setCustomerQuantity(Number(e.target.value))}
          placeholder="Quantity per Customer"
        />
        <button onClick={handleAddCustomer}>Add Customers</button>
      </div>

      <div className="system-controls">
        <button onClick={handleStartSystem}>Start System</button>
        <button onClick={handleStopSystem}>Stop System</button>
      </div>
    </div>
  );
};

export default Controls;