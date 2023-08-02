import React, { useState, useEffect } from 'react';
import { Container } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import TaskList from './components/TaskList';
import TaskForm from './components/TaskForm';
import { fetchTasks, createTask, deleteTask, setTaskToDone } from './api/api';
import styled from 'styled-components';

const StyledContainer = styled(Container)`
  margin-top: 40px;
  max-width: 500px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
  padding: 20px;
`;

const StyledHeading = styled.h1`
  text-align: center;
  margin-bottom: 20px;
`;

const App = () => {
  const [tasks, setTasks] = useState([]);

  useEffect(() => {
    fetchAllTasks();
  }, []);

  const fetchAllTasks = async () => {
    try {
      const tasks = await fetchTasks();
      setTasks(tasks);
    } catch (error) {
      console.error('Error fetching tasks:', error);
    }
  };

  const handleAddTask = async (title) => {
    try {
      const newTask = await createTask(title);
      setTasks((prevTasks) => [...prevTasks, newTask]);
    } catch (error) {
      console.error('Error adding task:', error);
    }
  };

  const handleDeleteTask = async (id) => {
    try {
      await deleteTask(id);
      setTasks((prevTasks) => prevTasks.filter((task) => task.id !== id));
    } catch (error) {
      console.error('Error deleting task:', error);
    }
  };

  const handleSetTaskToDone = async (id) => {
    try {
      await setTaskToDone(id);
      setTasks((prevTasks) =>
        prevTasks.map((task) =>
          task.id === id ? { ...task, status: 0 } : task
        )
      );
    } catch (error) {
      console.error('Error setting task to done:', error);
    }
  };

  return (
    <StyledContainer>
      <StyledHeading>To Do List</StyledHeading>
      <TaskForm onAddTask={handleAddTask} />
      <TaskList
        tasks={tasks}
        onDelete={handleDeleteTask}
        onSetTaskToDone={handleSetTaskToDone}
      />
    </StyledContainer>
  );
};

export default App;
