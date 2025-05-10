import { useState, useEffect } from 'react';

export default function ServiceProviderProfile() {
  const [serviceprovider, setServiceProvider] = useState(null);

  useEffect(() => {
    const storedServiceProvider = sessionStorage.getItem('serviceprovider');
    if (storedServiceProvider) {
      setServiceProvider(JSON.parse(storedServiceProvider));
    }
  }, []);

  if (!serviceprovider) {
    return (
      <div style={{ textAlign: 'center', marginTop: '50px' }}>
        Loading profile...
      </div>
    );
  }

  return (
    <div
      style={{
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        marginTop: '20px',
        fontFamily: 'Arial, sans-serif',
      }}
    >
      <h2 style={{ fontSize: '26px', color: '#333', marginBottom: '20px' }}>
        Service Provider Profile
      </h2>

      <div
        style={{
          backgroundColor: 'light grey',
          border: '1px solid black',
          borderRadius: '10px',
          padding: '20px',
          width: '350px',
        }}
      >
        <p><strong>Name:</strong> {serviceprovider.name}</p>
        <p><strong>Gender:</strong> {serviceprovider.gender}</p>
        <p><strong>Date of Birth:</strong> {serviceprovider.dob}</p>
        <p><strong>Email:</strong> {serviceprovider.email}</p>
        <p><strong>Username:</strong> {serviceprovider.username}</p>
        <p><strong>Mobile No:</strong> {serviceprovider.mobileno}</p>
        <p><strong>Company Name:</strong> {serviceprovider.company_name}</p>
        <p><strong>Company Location:</strong> {serviceprovider.company_location}</p>
      </div>
    </div>
  );
}
