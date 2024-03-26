import { Navigate } from 'react-router-dom'

export function ProtectedRoute({ children }) {
  return <Navigate to="/login" /> // login
}