<template>
  <form @submit.prevent="handleSubmit">
    <div class="container">
      <div class="row p-3 border">
        <div class="col">
          <input id="valueName" class="form-control" type="text" v-model="value.name" required>
          <label class="form-label" for="valueName">Value Name</label>
        </div>
        <div class="col">
          <input id="valueDescription" class="form-control" type="text" v-model="value.description">
          <label class="form-label" for="valueDescription">Value Description</label>
        </div>
        <div class="col">
          <input id="valuePicture" class="form-control" type="file" @change="loadImage">
          <label class="form-label" for="valuePicture">Value Picture</label>
        </div>
        <div class="col" v-if="!isUpdating">
          <select id="form1" class="btn btn-primary dropdown-toggle form-control" aria-expanded="false"
                  v-model="selectedCategory">
            <option v-for="category in categories" :key="category.id" :value="category.id">
              {{ category.name }}
            </option>
          </select>
          <label class="form-label" for="form1">Category</label>
        </div>
        <div class="col">
          <button class="btn btn-success btn-lg col-md-8" v-if="!isUpdating" type="submit">Create Value</button>
          <button class="btn btn-warning btn-lg col-md-8" v-else type="submit">Update Value</button>
        </div>
      </div>
    </div>
  </form>
</template>

<script>
import apiClient from '../services/api';

export default {
  props: {
    categories: {
      type: Array,
      required: true
    }
  },
  data() {
    return {
      value: {
        name: '',
        description: '',
        picture: '',
        categoryId: null
      },
      isUpdating: false,
      valueIdToUpdate: null,
      selectedCategory: null
    };
  },
  methods: {
    handleSubmit() {
      if (this.isUpdating) {
        this.updateValue();
      } else {
        this.createValue();
      }
    },
    createValue() {
      this.value.categoryId = this.selectedCategory;
      apiClient.post('/api/1/random/value/add', this.value)
          .then(response => {
            console.log('Value created:', response.data);
            this.$emit('refresh-categories');
            this.clearData();
          })
          .catch(error => {
            console.error('Error creating value:', error);
          });
    },
    updateValue() {
      apiClient.put(`/api/1/random/value/update/${this.valueIdToUpdate}`, this.value)
          .then(response => {
            console.log('Value updated:', response.data);
            this.$emit('refresh-categories');
            this.clearData();
          })
          .catch(error => {
            console.error('Error updating value:', error);
          });
    },
    populateValueFormForUpdate(value) {
      this.value = {...value};
      this.isUpdating = true;
      this.valueIdToUpdate = value.id;
      this.selectedCategory = this.categories.find(category => category.id === value.categoryId)
    },
    loadImage(event) {
      const file = event.target.files[0];
      const reader = new FileReader();

      reader.readAsDataURL(file);
      reader.onload = () => {
        this.value.picture = reader.result;
      };
      reader.onerror = (error) => {
        console.log('Error: ', error);
      };
    },
    clearData() {
      this.value = {name: '', description: '', picture: '', categoryId: null};
      this.isUpdating = false;
      this.valueIdToUpdate = null;
      this.selectedCategory = null;
    }
  }
};
</script>
