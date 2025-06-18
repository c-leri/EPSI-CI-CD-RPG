<script lang="ts">
  import { LucideRefreshCw } from 'lucide-svelte';
  import { roomApi } from '../Api/CallApi/GetGame';
  import type { Game } from '$lib/types/api';
  import { onMount } from 'svelte';
	import { gameApi } from '$lib/Api/CallApi/gameApi';

  let showModal = false;

  async function handleLoadGame(n: number){
    showModal = false;
    error = null
    let loadedGame: Game | null = null;
    try {
      loading = true
      const loadedGameResponse = await gameApi.loadGame(n);
      loadedGame = loadedGameResponse?.data ?? loadedGameResponse;
      
      localStorage.setItem('game', JSON.stringify(loadedGame));
      game = loadedGame;
    } catch (e) {
      
      error = e instanceof Error ? e.message : 'Une erreur est survenue lors du chargement du jeu';
    } finally { 
      loading = false;
    }
    console.log('Game loaded:', loadedGame);
    console.log('Game loaded:', game);

  }

  async function playNextRoom(n: number){
    showModal = false;
    error = null
    let loadedGame: Game | null = null;
    try {
      loading = true
      const loadedGameResponse = await gameApi.playNextRoom(n);
      loadedGame = loadedGameResponse?.data ?? loadedGameResponse;
      
      localStorage.setItem('game', JSON.stringify(loadedGame));
      game = loadedGame;
    } catch (e) {
      
      error = e instanceof Error ? e.message : 'Une erreur est survenue lors du chargement du jeu';
    } finally { 
      loading = false;
    }
    console.log('play next room:', loadedGame);
    console.log('play next room:', game);

  }

  let loading = false;
  let error: string | null = null;
  let game: Game | null = null;

  async function fetchGame() {
    loading = true;
    error = null;

    try {
      const gameId = localStorage.getItem('gameId');
      if (!gameId) {
        throw new Error('ID du jeu non trouvé');
      }

      const storedGame = localStorage.getItem('game');
      if (storedGame) {
        game = JSON.parse(storedGame);
      } else {
        const response = await roomApi.getGame(gameId);
        game = response.data;
        localStorage.setItem('game', JSON.stringify(game));
      }
    } catch (e) {
      error = e instanceof Error ? e.message : 'Une erreur est survenue';
    } finally {
      loading = false;
    }
  }

  onMount(() => {
    fetchGame();
  });
</script>


<div class="p-4 max-w-2xl mx-auto">
  <div class="flex justify-between items-center mb-6">
    <h1 class="text-2xl font-bold">Détails du Jeu</h1>
    <button
      on:click={() => showModal = true}
      class="flex items-center gap-2 px-4 py-2 bg-gradient-to-r from-blue-500 to-blue-700 text-white rounded-lg shadow hover:from-blue-600 hover:to-blue-800 transition-all duration-200 focus:outline-none focus:ring-2 focus:ring-blue-400"
    >
      <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor">
      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 4v16m8-8H4" />
      </svg>
      Charger une sauvegarde
    </button>

    {#if showModal}
      <div class="fixed inset-0 bg-black bg-opacity-40 flex items-center justify-center z-50">
      <div class="bg-black rounded-lg shadow-lg p-6 min-w-[200px]">
        <h2 class="text-lg font-bold mb-4">Choisissez votre numéro de sauvegarde</h2>
        <div class="flex gap-2 mb-4">
        {#each [1,2,3,4,5] as n}
          <button
          class="px-3 py-1 bg-blue-600 text-white rounded hover:bg-blue-700"
          on:click={() => handleLoadGame(n)}
          >
          {n}
          </button>
        {/each}
        </div>
        <button
        class="mt-2 px-3 py-1 bg-gray-300 rounded hover:bg-gray-400"
        on:click={() => showModal = false}
        >
        Annuler
        </button>
      </div>
      </div>
    {/if}


    <button
            on:click={fetchGame}
            class="flex items-center gap-2 px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 disabled:opacity-50"
            disabled={loading}
    >
      <LucideRefreshCw class={loading ? 'animate-spin' : ''} />
      Rafraîchir
    </button>
  </div>

  {#if error}
    <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative mb-4" role="alert">
      <span class="block sm:inline">{error}</span>
    </div>
  {/if}

  {#if loading}
    <div class="flex justify-center items-center p-8">
      <div class="animate-pulse text-gray-600">Chargement...</div>
    </div>
  {:else if game && !error}
  {#if game.pv === 0}
    <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative mb-4" role="alert">
      Vous êtes mort. Veuillez démarrer une nouvelle partie ou charger une autre sauvegarde.
    </div>
  {:else }
    <button class="px-3 py-1 bg-blue-600 text-white rounded hover:bg-blue-700" on:click={() => playNextRoom(game!.id)}> next room </button>
  {/if}
  
    <div class="bg-white shadow-lg rounded-lg p-6">
      <div class="border rounded-lg p-4 bg-gray-50">
        <h3 class="font-medium text-black">Jeu {game.id}</h3>
        <h3 class="font-medium text-black">PV: {game.pv}</h3>
        <h3 class="font-medium text-black">nbSalle: {game.nbSalle}</h3>
        
        {#if game.lastDiceRoll !== 7}
          <h3 class="font-medium text-black">lastDiceRoll: {game.lastDiceRoll}</h3>
        {/if}

        
        
       
      </div>
    </div>
  {/if}
</div>