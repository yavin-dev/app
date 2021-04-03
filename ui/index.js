/**
 * Copyright 2021 Yahoo Holdings Inc.
 * Licensed under the terms of the MIT license. See accompanying LICENSE.md file for terms.
 */

const express = require('express');
const useConfigMiddleware = require('./server/use-config-middleware');
const useCompressionMiddleware = require('./server/use-compression-middleware');
const useCacheMiddleware = require('./server/use-cache-middleware');
const useStaticAssetMiddleware = require('./server/use-static-asset-middleware');
const useFallbackResourceMiddleware = require('./server/use-fallback-resource-middleware');

process.chdir(__dirname);

const app = express();

// Handle environment config
useConfigMiddleware(app);

//compression and static asset cache
useCompressionMiddleware(app);
useCacheMiddleware(app);

// Serve assets
useStaticAssetMiddleware(app, __dirname);

/*
 * If a requested path can't be found,
 * let Ember determine if the path is a valid route
 */
useFallbackResourceMiddleware(app, __dirname);

const port = process.env.PORT || 80;
app.listen(port);

// eslint-disable-next-line no-console
console.log('Hey, listening on port: ' + port);
