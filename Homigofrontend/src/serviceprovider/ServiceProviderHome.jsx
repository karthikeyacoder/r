import { useState, useEffect } from 'react';

export default function ServiceProviderHome() 
{
     const [serviceprovider, setServiceProvider] = useState("");
     
     useEffect(() => {
       const storedServiceProvider = sessionStorage.getItem('serviceprovider');
       if (storedServiceProvider) {
         setServiceProvider(JSON.parse(storedServiceProvider));
       }
     }, []);
     
  return (
    <div>
      <h3>Hello {serviceprovider.name}</h3>
    </div>
  )
}
