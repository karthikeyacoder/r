import { BrowserRouter } from "react-router-dom";
import MainNavBar from "./main/MainNavBar";
import AdminNavBar from "./admin/AdminNavBar";
import CustomerNavBar from "./customer/CustomerNavBar";
import ServiceProviderNavBar from "./serviceprovider/ServiceProviderNavBar";
import { AuthProvider, useAuth } from "./contextapi/AuthContext";

function AppContent() 
{
  const { isAdminLoggedIn, isCustomerLoggedIn, isServiceProviderLoggedIn } = useAuth();

  return (
    <div>
      <BrowserRouter>
        {isAdminLoggedIn ? (
          <AdminNavBar />
        ) : isCustomerLoggedIn ? (
          <CustomerNavBar />
        ) : isServiceProviderLoggedIn ? (
          <ServiceProviderNavBar />
        ) : (
          <MainNavBar />
        )}
      </BrowserRouter>
    </div>
  );
}

function App() {
  return (
    <AuthProvider>
      <AppContent />
    </AuthProvider>
  );
}

export default App;