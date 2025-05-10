import { useState, useEffect } from 'react';
import axios from 'axios';
import config from '../config';

export default function AddService() {
  const [formData, setFormData] = useState({
    category: '',
    serviceName: '',
    description: '',
    serviceDuration: '',
    servicePrice: ''
  });

  const [serviceprovider, setServiceProvider] = useState("");
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  useEffect(() => {
    const storedServiceProvider = sessionStorage.getItem('serviceprovider');
    if (storedServiceProvider) {
      setServiceProvider(JSON.parse(storedServiceProvider));
    }
  }, []);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.id]: e.target.value });
  };

  const handleSubmit = async (e) => 
  {
    e.preventDefault();
  
    const serviceData = {
      ...formData,
      serviceProvider_id: serviceprovider.id // from session storage
    };

    try {
      const response = await axios.post(`${config.url}/serviceprovider/addservice`, serviceData);
      if (response.status === 200) {
        setMessage(response.data);
        setError('');
        setFormData({
          category: '',
          serviceName: '',
          description: '',
          serviceDuration: '',
          servicePrice: ''
        });
      }
    } catch (error) {
      setMessage('');
      if (error.response) {
        setError(error.response.data);
      } else {
        setError("An unexpected error occurred.");
      }
    }
  };

  return (
    <div>
      <h3 style={{ textAlign: "center", textDecoration: "underline" }}>Add New Service</h3>
      {
        message ?
          <p style={{ textAlign: "center", color: "green", fontWeight: "bolder" }}>{message}</p> :
          <p style={{ textAlign: "center", color: "red", fontWeight: "bolder" }}>{error}</p>
      }
      <form onSubmit={handleSubmit}>
        <div>
          <label>Category</label>
          <input type="text" id="category" value={formData.category} onChange={handleChange} required />
        </div>
        <div>
          <label>Service Name</label>
          <input type="text" id="serviceName" value={formData.serviceName} onChange={handleChange} required />
        </div>
        <div>
          <label>Description</label>
          <textarea id="description" value={formData.description} onChange={handleChange} required />
        </div>
        <div>
          <label>Service Duration</label>
          <input type="text" id="serviceDuration" value={formData.serviceDuration} onChange={handleChange} required />
        </div>
        <div>
          <label>Service Price</label>
          <input type="number" step="0.01" id="servicePrice" value={formData.servicePrice} onChange={handleChange} required />
        </div>
        <button type="submit">Add Service</button>
      </form>
    </div>
  );
}
