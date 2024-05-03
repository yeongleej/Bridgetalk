import { useQuery } from 'react-query';
import axios from 'axios';

const fetchLetters = async () => {
  const { data } = await axios.get('/api/letters', {
    headers: {
      Authorization:
        'Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1dWlkIjoiYWVhODEwMTEtMDc0My00NDIzLThjNDEtMTcwZmY3OTBhNWY5IiwiaWF0IjoxNzE0NjMxODEzLCJleHAiOjE3MTU4NDE0MTN9.s9mghadgwBOL6eqtrgq6YFW6mV091FUflyEZgB_pWMg',
    },
  });
  return data;
};

export const useLetters = () => {
  return useQuery('letters', fetchLetters);
};
