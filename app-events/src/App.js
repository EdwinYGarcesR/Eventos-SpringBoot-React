import { useEffect } from 'react'
import { BrowserRouter } from 'react-router-dom'
import { auth } from './firebase/firebase'
import Layout from './layouts/Layout'
import AppRouter from './routes/AppRouter'
import StoreProvider, { useDispatch } from './store/StoreProvider'
import { types } from './store/StoreReducer'

function App() {
  return (
    <div>
      <StoreProvider>
        <BrowserRouter>
          <Layout>
            <AppRouter />
          </Layout>
        </BrowserRouter>
      </StoreProvider>
    </div>
  )
}

export default App
