# Free test deployment (Render)

The root `render.yaml` defines a Render Blueprint for the frontend, API gateway,
backend services, and PostgreSQL database. The GitHub repository can remain
private.

## Deploy

1. Commit and push the application and deployment files to the private GitHub
   repository. Do not commit `.env` files, API keys, or database credentials.
2. Sign in to <https://dashboard.render.com> with a GitHub account that can
   access the repository.
3. Select **New > Blueprint**, choose the `smart-agri` repository and the
   branch containing `render.yaml`, then review and apply the Blueprint.
4. Wait for the API gateway and backend services to become healthy, then open
   the frontend URL from the Render dashboard.
5. To enable farmer-agent AI features, configure `OLLAMA_BASE_URL` to a
   reachable hosted Ollama-compatible endpoint and provide the needed model.
   `TAVILY_API_KEY` is optional for web search. Never use `localhost` for a
   model service hosted separately from Render.

## Free-tier limitations

- Free web services sleep after 15 minutes without traffic. A cold start may
  take about a minute; the frontend uses a two-minute API timeout for testing.
- Render's 750 free instance hours are shared across free web services, so
  continuous activity across this multi-service deployment can use the
  allowance quickly.
- The free PostgreSQL database is limited to 1 GB and expires after 30 days.
  Export needed data before expiry; do not use this deployment for production
  or important data.
- H2-backed crop, farm, sensor, and irrigation data is ephemeral and can be
  lost when a free service restarts or redeploys.
- Service and frontend URLs are public, but the source repository stays
  private. Render generates the JWT secret; do not commit secrets.