import { useEffect, useState } from 'react';
import axios from 'axios';
import config from '../config';

export default function ViewServicesByProvider() {
  const [services, setServices] = useState([]);
  const [error, setError] = useState('');
  const [serviceProviderId, setServiceProviderId] = useState(null);

  useEffect(() => {
    const storedServiceProvider = sessionStorage.getItem('serviceprovider');
    if (storedServiceProvider) {
      const serviceprovider = JSON.parse(storedServiceProvider);
      setServiceProviderId(serviceprovider.id);
      fetchServices(serviceprovider.id);
    }
  }, []);

  const fetchServices = async (serviceProviderId) => {
    try {
      const response = await axios.get(`${config.url}/serviceprovider/viewservicesbyprovider/${serviceProviderId}`);
      setServices(response.data);
      setError('');
    } catch (err) {
      setError('Failed to fetch your services');
      setServices([]);
    }
  };

  return (
    <div>
      <h3 style={{ textAlign: "center", textDecoration: "underline" }}>My Services</h3>
      {error && <p style={{ color: 'red', textAlign: 'center' }}>{error}</p>}
      {services.length === 0 ? (
        <p style={{ textAlign: 'center' }}>No services added yet.</p>
      ) : (
        <table style={{ margin: '0 auto', width: '90%', textAlign: 'left' }}>
          <thead>
            <tr>
              <th>Service ID</th>
              <th>Category</th>
              <th>Service Name</th>
              <th>Description</th>
              <th>Duration</th>
              <th>Price</th>
              <th>Service Provider Name</th>
              <th>Service Provider Email</th>
            </tr>
          </thead>
          <tbody>
            {services.map((service) => (
               <tr key={service.id}>
               <td>{service.id}</td>
               <td>{service.category}</td>
               <td>{service.serviceName}</td>
               <td>{service.description}</td>
               <td>{service.serviceDuration}</td>
               <td>{service.servicePrice}</td>
               <td>{service.serviceProvider?.name}</td>
               <td>{service.serviceProvider?.email}</td>
             </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}
